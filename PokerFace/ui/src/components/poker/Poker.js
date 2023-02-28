import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";

function Poker() {
  const [userData, setUserData] = useState({ name: "", roomId: "", role: "" });
  const location = useLocation();

  useEffect(() => {
    const setData = () => {
      const { name, roomId, role } = location.state;
      setUserData({ name: name, roomId: roomId, role: role });
    };
    setData();
    console.log(userData);
  }, []);

  return (
    <>
      {userData ? (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea />
              {userData.role == "moderator" ? <VotingControls /> : ""}
            </div>
            <PlayerList />
          </div>
        </>
      ) : (
        <h3 style={{ textAlign: "center" }}>Unauthorized Login!</h3> //redirect to login page
      )}
    </>
  );
}

export default Poker;
