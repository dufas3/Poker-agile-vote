import Nav from "../header/Nav";
import "../GlobalCSS.css";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import GetCards from "../../api/get/getCards";
import getSessionState from "../../api/get/getSessionState";
import GetActiveCards from "../../api/get/getActiveCards";
import GetSessionUsers from "../../api/get/getSessionUsers";
import { MethodNames } from "../../common/methodNames";
import { signalRConnection } from "../../api/signalR/signalRHub";
import { useSearchParams } from "react-router-dom";
import getUser from "../../api/get/getUser";
import { getUserId, setUserId } from "../../common/UserId";

const Poker = () => {
  const [cards, setCards] = useState([]);
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState([]);
  const [sessionState, setSessionState] = useState(0);
  const [activeCards, setActiveCards] = useState([]);
  const [roomId, setRoomId] = useState("");
  const [searchParams] = useSearchParams();
  const [navig, setNavig] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    const setData = async () => {
      if (!signalRConnection) await signalRConnection.start();

      //api calls
      await setUpUser();
      await getCards();
      await setUserList();
      await setSessionStateFromApi();
      await getActiveCards();
    };
    setData();
  }, []);

  useEffect(() => {
    setRoomId(searchParams.get("roomId"));
  }, [roomId]);
  useEffect(() => {
    if (getUserId() == null) {
      if (searchParams.get("roomId") === undefined) {
        setNavig(navigate("/Login", { replace: true }));
      } else {
        setNavig(
          navigate("/Join?roomId=" + searchParams.get("roomId"), {
            replace: true,
          })
        );
      }
    }
  }, []);

  useEffect(() => {
    //to force rerender when response is awaited
  }, [users]);

  const setUserList = async () => {
    let response = await GetSessionUsers({
      roomId: searchParams.get("roomId"),
    });
    if (response) setUsers(response);
  };

  const setSessionStateFromApi = async () => {
    let response = await getSessionState({
      roomId: searchParams.get("roomId"),
    });
    setSessionState(response);
  };
  const setUpUser = async () => {
    let response = await getUser({
      userId: getUserId(),
      roomId: searchParams.get("roomId"),
    });
    console.log("set up user", response);
    setUser(response);
  };

  const getCards = async () => {
    let response = await GetCards();
    if (response) {
      setCards(response);
    }
  };

  const getActiveCards = async () => {
    let activeCardsResponse = await GetActiveCards({
      roomId: searchParams.get("roomId"),
    });
    if (activeCardsResponse) {
      setActiveCards(activeCardsResponse);
    }
  };

  //-----------------Event Listeners-------------------------------------
  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.PlayerListUpdate, () => {
        setUserList();
      });
    };
    getData();
  }, []);

  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.SessionStateUpdate, () => {
        setSessionStateFromApi();
      });
    };
    getData();
  }, []);

  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.ActiveCardsUpdate, () => {
        getActiveCards();
      });
    };
    getData();
  }, []);

  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.SessionLogout, () => {
        console.log("ALLLO");
        setUserId(undefined);
        setNavig(navigate("/Login", { replace: true }));
      });
    };
    getData();
  }, []);

  return (
    <>
      {roomId && (
        <>
          <Nav user={user} />
          <div className="poker">
            <div className="voting">
              <VotingArea
                cards={activeCards}
                userId={user.id}
                roomId={searchParams.get("roomId")}
                sessionState={sessionState}
                userList={users}
              />
              {user.role == "moderator" ? (
                <VotingControls
                  cards={cards}
                  roomId={searchParams.get("roomId")}
                  activeCards={activeCards}
                  session={sessionState}
                  key={activeCards.join(",")}
                />
              ) : (
                ""
              )}
            </div>
            <PlayerList sessionState={sessionState} userList={users} />
          </div>
        </>
      )}
    </>
  );
};

export default Poker;
