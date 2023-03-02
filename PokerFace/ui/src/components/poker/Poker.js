import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import GetCards from "../../api/getCards";
import getSessionState from "../../api/getSessionState";

function Poker() {
  const [userData, setUserData] = useState({ name: "", roomId: "", role: "", userId: ""});
  const [cards, setCards] = useState([]);
  const location = useLocation();

  useEffect(() => {
    const setData = () => {
      setUserData({
        name: location.state.name,
        roomId: location.state.roomId,
        role: location.state.role,
        userId: location.state.userId,
      });
    };


    const setCardsFromApi = async () => {
      await getSessionState({roomId: location.state.roomId})
      let response = await GetCards({
        roomId: location.state.roomId,
      });
      if (response) {
        setCards(response);
      }
    };
    setData();
    setCardsFromApi();
  }, []);

  return (
    <>
      {userData && (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea cards={{cards: cards, userId: location.state.userId, roomId: location.state.roomId}} />
              {userData.role == "moderator" ? (
                <VotingControls cards={{cards: cards, roomId: location.state.roomId}} />
              ) : (
                ""
              )}
            </div>
            <PlayerList userData={location.state} />
          </div>
        </>
      )}
    </>
  );
}

export default Poker;
