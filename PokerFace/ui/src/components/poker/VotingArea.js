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
          <div className="row-1">
            {cards.map((card) =>
              card.id < 8 ? (
                <Card
                  cardValue={card.value}
                  cardId={card.id}
                  userId={userId}
                  sessionState={sessionState}
                  roomId={roomId}
                />
              ) : (
                " "
              )
            )}
          </div>
          <div className="row-2">
            {cards.map((card) =>
              card.id > 7 ? (
                <Card
                  cardValue={card.value}
                  cardId={card.id}
                  userId={userId}
                  sessionState={sessionState}
                  roomId={roomId}
                />
              ) : (
                " "
              )
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
