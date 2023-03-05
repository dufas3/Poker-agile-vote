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

const Poker = () => {
  const [userData, setUserData] = useState({
    name: "",
    roomId: "",
    role: "",
    userId: "",
  });
  const [cards, setCards] = useState([]);
  const [users, setUsers] = useState([]);
  const [sessionState, setSessionState] = useState(0);
  const [activeCards, setActiveCards] = useState([]);
  const location = useLocation();

  const setUserList = async () => {
    let response = await GetSessionUsers({
      id: location.state.roomId,
    });
    if (response) setUsers(response);
  };

  const setSessionStateFromApi = async () => {
    let response = await getSessionState({ roomId: location.state.roomId });
    setSessionState(response);
  };

  const getCards = async () => {
    let response = await GetCards({
      roomId: location.state.roomId,
    });
    if (response) {
      setCards(response);
    }
  };

  const getActiveCards = async () => {
    let activeCardsResponse = await GetActiveCards({
      roomId: location.state.roomId,
    });
    if (activeCardsResponse) {
      setActiveCards(activeCardsResponse);
    }
  };

  useEffect(() => {
    console.log("setting users");
    setUserList();
  }, []);

  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.PlayerListUpdate, () => {
        setUserList();
      });
    };
    getData();
  }, [users]);

  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.SessionStateUpdate, () => {
        setSessionState();
      });
    };
    getData();
  }, [sessionState]);

  useEffect(() => {
    const getData = async () => {
      signalRConnection.on(MethodNames.ActiveCardsUpdate, () => {
        getActiveCards();
      });
    };
    getData();
  }, [activeCards]);

  useEffect(() => {
    const setData = async () => {
      if (!signalRConnection) await signalRConnection.start();
      await getCards();
      await getActiveCards();
      await setUserList();
      await setSessionStateFromApi();

      setUserData({
        name: location.state.name,
        roomId: location.state.roomId,
        role: location.state.role,
        userId: location.state.userId,
      });
    };
    setData();
  }, []);

  return (
    <>
      {userData && (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea
                cards={activeCards}
                userId={location.state.userId}
                roomId={location.state.roomId}
                sessionState={sessionState}
                userList={users}
              />
              {userData.role == "moderator" ? (
                <VotingControls
                  cards={cards}
                  roomId={location.state.roomId}
                  activeCards={activeCards}
                  session={sessionState}
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
