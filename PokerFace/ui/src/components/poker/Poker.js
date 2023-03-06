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
import getSession from "../../api/get/getSession";
import {useSearchParams} from "react-router-dom";
import GetSession from "../../api/get/getSession";

const Poker = () => {
  const [sessionData, setSessionData] = useState({
    roomId: "",
    users: [],
    cards: [],
    sessionState: "",
  });
  const [cards, setCards] = useState([]);
  const [users, setUsers] = useState([]);
  const [sessionState, setSessionState] = useState(0);
  const [activeCards, setActiveCards] = useState([]);

  const [searchParams] = useSearchParams();

  const setUserList = async () => {
    let response = await GetSessionUsers({
      id: sessionData.roomId, //this
    });
    if (response) setUsers(response);
  };

  const setSessionStateFromApi = async () => {
    let response = await getSessionState({ roomId: sessionData.roomId });
    setSessionState(response);
  };

  const getCards = async () => {
    let response = await GetCards({
      roomId: sessionData.roomId,
    });
    if (response) {
      setCards(response);
    }
  };

  const getActiveCards = async () => {
    let activeCardsResponse = await GetActiveCards({
      roomId: sessionData.roomId,
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
        setSessionStateFromApi();
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
    console.log("searchParams ",searchParams.get("roomId"))
    const setData = async () => {
      if (!signalRConnection) await signalRConnection.start();

      let response = await GetSession(searchParams.get("roomId"));
      console.log("responseresponseresponse: ",response)


      setSessionData({
        roomId: searchParams.get("roomId"),
        users: response.users,
        cards: response.cards,
        sessionState: response.state,
      });
    };
    setData();
  }, []);

  return (
    <>
      {sessionData && (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea
                cards={sessionData.cards}
                userId={localStorage.getItem("userId")}
                roomId={sessionData.roomId}
                sessionState={sessionData.sessionState}
                userList={sessionData.users}
              />
              {sessionData.role == "moderator" ? (
                <VotingControls
                  cards={sessionData.cards}
                  roomId={sessionData.roomId}
                  activeCards={sessionData.cards}
                  session={sessionData.sessionState}
                />
              ) : (
                ""
              )}
            </div>
            <PlayerList sessionState={sessionData.sessionState} userList={sessionData.users} />
          </div>
        </>
      )}
    </>
  );
};

export default Poker;
