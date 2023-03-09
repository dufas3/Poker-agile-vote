import './VotingControls.css';
import '../GlobalCSS.css'
import { useEffect, useState } from "react";
import { SessionState } from "../../common/sessionState";
import setSessionState from "../../api/set/setSessionState";
import ClearSessionVotes from "../../api/clearSessionVotes";
import SetActiveCards from "../../api/set/setActiveCards";

const VotingControls = ({ cards, activeCards, roomId }) => {
  const [inSettings, setInSettings] = useState(false);
  const [selectedCheckboxes, setSelectedCheckboxes] = useState([]);

  useEffect(() => {
    setUpAlreadyActiveCards();
  }, [activeCards]);

  useEffect(() => {
    setUpAlreadyActiveCards();
  }, []);

  const handleSetAllCards = () => {
    //  To Do (set all cards for players to vote)
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

  const handleClearVotes = async () => {
    await setSessionState({
      roomId: roomId,
      state: SessionState.VOTESTATE,
    });
    await ClearSessionVotes({ roomId: roomId });
  };

  const handleCheckboxChange = (event, id) => {
    if (event) {
      const value = event.target.value;
      const checked = event.target.checked;
      if (checked) {
        setSelectedCheckboxes([...selectedCheckboxes, value]);
      } else {
        setSelectedCheckboxes(
          selectedCheckboxes.filter((item) => item !== value)
        );
      }
    } else {
      let checked = !(selectedCheckboxes.filter((x) => x == id).length > 0);
      if (checked) {
        setSelectedCheckboxes([...selectedCheckboxes, id]);
      } else {
        setSelectedCheckboxes(selectedCheckboxes.filter((item) => item !== id));
      }
    }
  };

  const handleChangeCardsSave = async () => {
    let ids = [];
    selectedCheckboxes.map((cb) => {
      if (!ids.includes(cb)) ids.push(cb);
    });
    setInSettings(false);

    //if (ids.length == 0) return; //toast error
    await SetActiveCards({
      roomId: roomId,
      cardIds: ids,
    });

    await ClearSessionVotes({ roomId: roomId });
    setUpAlreadyActiveCards();
  };

  const handleChangeCardsCancel = async () => {
    if (inSettings) {
      setInSettings(false);
    } else {
      setInSettings(true);
    }
    setUpAlreadyActiveCards();
  };

  //sets up all cards
  const setUpAlreadyActiveCards = async () => {
    activeCards?.map((card) => {
      handleCheckboxChange(undefined, card.id.toString());
    });
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
                  onClick={handleClearVotes}
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
                  onChange={handleCheckboxChange}
                  value={card.id.toString()}
                  className="form-check-input"
                  type="checkbox"
                  checked={selectedCheckboxes.includes(card.id.toString())}
                  id="flexCheckDefault"
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
};

export default VotingControls;
