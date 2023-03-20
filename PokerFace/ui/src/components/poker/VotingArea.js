import "./Poker.css";
import Card from "../card/Card";
import VotingSummary from "./VotingSummary";
import "./VotingArea.css";
import "../GlobalCSS.css";

const VotingArea = ({ sessionState, roomId, cards, userId, userList }) => {
  return (
    <div className="voting-area border rounded bg-light">
      {sessionState != 1 ? (
        <div className="voting-area">
          <div className="cards-container">
            {cards.map((card) =>
                <Card
                  cardValue={card.value}
                  cardId={card.id}
                  userId={userId}
                  sessionState={sessionState}
                  roomId={roomId}
                />
            )}
          </div>
        </div>
      ) : (
        <VotingSummary data={{ users: userList }} />
      )}
    </div>
  );
};

export default VotingArea;
