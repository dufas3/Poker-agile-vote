import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";

function Poker() {
    return (
        <>
            <Nav/>
            <div className="poker">
            <div className="voting">
                <VotingArea/>
                <VotingControls/>
            </div>
            <PlayerList/>
            </div>
        </>

    );
}

export default Poker;