import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import GetCards from "../../api/get/getCards";
import getSessionState from "../../api/get/getSessionState";
import GetActiveCards from "../../api/get/getActiveCards";
import GetSessionUsers from "../../api/get/getSessionUsers";
import { MethodNames } from "../../common/methodNames";
import { signalRConnection } from "../../api/signalR/signalRHub";
import { useSearchParams } from "react-router-dom";
import GetUser from "../../api/get/getUser";
import { atom } from "jotai";

const Poker = () => {
  const [cards, setCards] = useState([]);
  const [role, setRole] = useState("");
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState({ name: "", role: "" });
  const [sessionState, setSessionState] = useState(0);
  const [activeCards, setActiveCards] = useState([]);
  const [roomId, setRoomId] = useState("");
  const [searchParams] = useSearchParams();
  //const [activeCardsAtom,setActiveCardsAtom] = atom([])

  useEffect(() => {
    const setData = async () => {
      if (!signalRConnection) await signalRConnection.start();

      let respone = await GetUser({ userId: localStorage.getItem("userId") });
      console.log("response ", respone);
      setUser(respone);
      respone.name.includes("@") ? setRole("moderator") : setRole("user");

      //api calls
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

  const getCards = async () => {
    let response = await GetCards({
      roomId: searchParams.get("room"),
    });
    if (response) {
      setCards(response);
      console.log("setting cards", cards);
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
      console.log("updated cards");
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
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea
                cards={activeCards}
                userId={localStorage.getItem("userId")}
                roomId={searchParams.get("room")}
                sessionState={sessionState}
                userList={users}
              />
              {role == "moderator" ? (
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
