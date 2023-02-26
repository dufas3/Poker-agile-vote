import "./Poker.css";
import { useState } from "react";

function VotingControls() {
  const [inSettings, setInSettings] = useState(false);

  const test = () => {
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
          <button onClick={test} className="settings-button btn btn-light">
            <i className="fa-solid fa-gear settings"></i>
          </button>
          <div className="center-contents">
            <div className="voting-buttons">
              <div className="row-top">
                <button className="flip-cards btn btn-outline-primary">
                  <h6 className="center-text">Flip Cards</h6>
                </button>

                <button className="clear-votes btn btn-outline-primary">
                  <h6 className="center-text">Clear Votes</h6>
                </button>
              </div>

              <button className="finish-voting btn btn-primary">
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
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                Use all cards
              </label>
            </div>

            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                0
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                1/2
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                1
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                2
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                3
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                5
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                8
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                13
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                20
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                40
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                100
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                ?
              </label>
            </div>
            <div className="form-check border rounded bg-light">
              <input
                className="form-check-input"
                type="checkbox"
                value="1"
                id="flexCheckDefault"
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                Coffee
              </label>
            </div>
          </div>
          <div className="exit-settings w-50">
            <button onClick={test} className="w-25 btn btn-primary">
              <h6 className="center-text">Save</h6>
            </button>
            <button onClick={test} className="w-25 btn btn-outline-primary">
              <h6 className="center-text">Cancel</h6>
            </button>
          </div>
        </div>
      )}
    </>
  );
}

export default VotingControls;
