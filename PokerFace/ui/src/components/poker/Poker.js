import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";

function Poker() {
  return (
    <>
      {localStorage.getItem("roomId") ? (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea />
              {localStorage.getItem("role") == "moderator" ? (
                <VotingControls />
              ) : (
                ""
              )}
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
