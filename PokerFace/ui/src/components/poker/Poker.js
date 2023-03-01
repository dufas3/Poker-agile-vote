import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import GetCards from "../../api/getCards";

function Poker() {
  const [userData, setUserData] = useState({ name: "", roomId: "", role: "" });
  const [cards, setCards] = useState([]);
  const location = useLocation();

  useEffect(() => {
    const setData = () => {
      setUserData({
        name: location.state.name,
        roomId: location.state.roomId,
        role: location.state.role,
      });
    };

    const setCardsFromApi = async () => {
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
      {userData ? (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea cards={cards} />
              {userData.role == "moderator" ? (
                <VotingControls cards={cards} />
              ) : (
                ""
              )}
            </div>
            <PlayerList userData={location.state} />
          </div>
        </>
      ) : (
        <h3 style={{ textAlign: "center" }}>Unauthorized Login!</h3> //redirect to login page
      )}
    </>
  );
}

export default Poker;
