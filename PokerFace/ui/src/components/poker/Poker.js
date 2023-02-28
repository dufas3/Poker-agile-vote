import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";

function Poker() {

  let userData = JSON.parse(localStorage.getItem("userData"))

  console.log(userData["role"])

  return (
    <>
      {userData["roomId"] ? (
        <>
          <Nav />
          <div className="poker">
            <div className="voting">
              <VotingArea />
              {userData["role"] == "moderator" ? (
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
