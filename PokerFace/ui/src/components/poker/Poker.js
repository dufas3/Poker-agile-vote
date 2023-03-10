import Nav from "../header/Nav";
import '../GlobalCSS.css'
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import {useNavigate} from "react-router-dom";
import { useEffect, useState } from "react";
import GetCards from "../../api/get/getCards";
import getSessionState from "../../api/get/getSessionState";
import GetActiveCards from "../../api/get/getActiveCards";
import GetSessionUsers from "../../api/get/getSessionUsers";
import { MethodNames } from "../../common/methodNames";
import { signalRConnection } from "../../api/signalR/signalRHub";
import { useSearchParams } from "react-router-dom";
import getUser from "../../api/get/getUser";

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
    setRoomId(searchParams.get("room"));
  }, [roomId]);
  useEffect(()=>{
    if(localStorage.getItem("userId") == null){
      if (searchParams.get("room") == undefined) {
        setNavig(navigate("/Login", {replace: true}));
      } else {
        setNavig(navigate("/Join?room=" + searchParams.get("room"), {replace: true}));
      }
    }
  },[])

  const setUserList = async () => {
    let response = await GetSessionUsers({
      id: searchParams.get("room"),
    });
    if (response) setUsers(response);
  };

  const setSessionStateFromApi = async () => {
    let response = await getSessionState({ roomId: searchParams.get("room") });
    setSessionState(response);
  };
  const setUpUser = async () => {
    let response = await getUser({userId: localStorage.getItem("userId")});
    setUser(response)
  };

  const getCards = async () => {
    let response = await GetCards({
      roomId: searchParams.get("room"),
    });
    if (response) {
      setCards(response);
    }
  };

  const getActiveCards = async () => {
    let activeCardsResponse = await GetActiveCards({
      roomId: searchParams.get("room"),
    });
    if (activeCardsResponse) {
      setActiveCards(activeCardsResponse);
    }
  };

  //-----------------Event Listeners--------------------------------------
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

  return (
    <>
      {roomId && (
        <>
          <Nav user = {user}/>
          <div className="poker">
            <div className="voting">
              <VotingArea
                cards={activeCards}
                userId={localStorage.getItem("userId")}
                roomId={searchParams.get("room")}
                sessionState={sessionState}
                userList={users}
              />
              {user.role == "moderator" ? (
                <VotingControls
                  cards={cards}
                  roomId={searchParams.get("room")}
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
