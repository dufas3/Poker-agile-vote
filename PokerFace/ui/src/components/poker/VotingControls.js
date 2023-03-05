import "./Poker.css";
import { useEffect, useState } from "react";
import { SessionState } from "../../common/sessionState";
import setSessionState from "../../api/set/setSessionState";
import ClearSessionVotes from "../../api/clearSessionVotes";
import setActiveCards from "../../api/set/setActiveCards";

function VotingControls({ cards, activeCards, roomId }) {
  const [inSettings, setInSettings] = useState(false);
  let sessionActiveCards = [];

  useEffect(() => {
    sessionActiveCards = activeCards;
  }, []);

  const handleSetAllCards = () => {
    sessionActiveCards = [];

    cards.forEach((card) => {
      sessionActiveCards.push(card.id);
    });
  };

  const handleFlipCards = async () => {
    await setSessionState({
      roomId: roomId,
      state: SessionState.FINILIZESTATE,
    });
  };
  const handleFinish = async () => {
    await setSessionState({
      roomId: roomId,
      state: SessionState.FINISHSTATE,
    });
    await ClearSessionVotes({ roomId: roomId });
  };
  const handleResetCards = async () => {
    await setSessionState({
      roomId: roomId,
      state: SessionState.VOTESTATE,
    });
    await ClearSessionVotes({ roomId: roomId });
  };

  const handleChangeCardsSave = async () => {
    if (inSettings) {
      setInSettings(false);
      await setActiveCards({
        roomId: roomId,
        cards: sessionActiveCards,
      });
      await ClearSessionVotes({ roomId: roomId });
    } else {
      setInSettings(true);
    }
  };
  const handleChangeCardsCancel = async () => {
    if (inSettings) {
      setInSettings(false);
    } else {
      setInSettings(true);
    }
  };
  return (
    <>
      {!inSettings ? (
        <div className="voting-container border rounded bg-light">
          <button
            onClick={handleChangeCardsCancel}
            className="settings-button btn btn-light"
          >
            <i className="fa-solid fa-gear settings"></i>
          </button>
          <div className="center-contents">
            <div className="voting-buttons">
              <div className="row-top">
                <button
                  className="flip-cards btn btn-outline-primary"
                  onClick={handleFlipCards}
                >
                  <h6 className="center-text">Flip Cards</h6>
                </button>
                <button
                  className="clear-votes btn btn-outline-primary"
                  onClick={handleResetCards}
                >
                  <h6 className="center-text">Clear Votes</h6>
                </button>
              </div>
              <button
                className="finish-voting btn btn-primary"
                onClick={handleFinish}
              >
                <h6 className="center-text">Finish Voting</h6>
              </button>
            </div>
          </div>
        </div>
      ) : (
        <div className="voting-container border rounded bg-light">
          <div className="options">
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
                onClick={handleSetAllCards}
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                Use all cards
              </label>
            </div>
            {cards.map((card) => (
              <div className="form-check border rounded bg-light">
                <input
                  onClick={() => {
                    if (sessionActiveCards.includes(card.id)) {
                      sessionActiveCards.splice(
                        sessionActiveCards.findIndex((id) => id === card.id)
                      );
                    } else {
                      sessionActiveCards.push(card.id);
                    }
                  }}
                  className="form-check-input"
                  type="checkbox"
                  value="true"
                  id="flexCheckDefault"
                  onChange={() => {}}
                />
                <label className="form-check-label" htmlFor="flexCheckDefault">
                  {card.value}
                </label>
              </div>
            ))}
          </div>
          <div className="exit-settings w-50">
            <button
              onClick={handleChangeCardsSave}
              className="w-25 btn btn-primary"
            >
              <h6 className="center-text">Save</h6>
            </button>
            <button
              onClick={handleChangeCardsCancel}
              className="w-25 btn btn-outline-primary"
            >
              <h6 className="center-text">Cancel</h6>
            </button>
          </div>
        </div>
      )}
    </>
  );
}

export default VotingControls;
