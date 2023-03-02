import "./Poker.css";
import {useEffect, useState} from "react";
import ControllSetting from "./controllSetting";
import getSessionState from "../../api/get/getSessionState";
import {SessionState} from "../../common/sessionState";
import setSessionState from "../../api/set/setSessionState";

function VotingControls(props) {
    const [inSettings, setInSettings] = useState(false);
    const [session, setSession] = useState(0)

    useEffect(() => {
        console.log("State: ", session)
    },[session])



    useEffect(() => {
        const getData = async () => {
            let response = await getSessionState({roomId: props.cards.roomId});
            setSession(response);

        }
        getData();
    }, [])
    const handleFlipCards = async () => {
        await setSessionState({roomId: props.cards.roomId, state: SessionState.FINILIZESTATE})
    }
    const handleFinish = async () => {
        await setSessionState({roomId: props.cards.roomId, state: SessionState.FINISHSTATE})
    }
    const handleResetCards = async () => {
        await setSessionState({roomId: props.cards.roomId, state: SessionState.VOTESTATE})
    }

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

                                <button className="flip-cards btn btn-outline-primary" onClick={handleFlipCards}>
                                    <h6 className="center-text">Flip Cards</h6>
                                </button>

                                <button className="clear-votes btn btn-outline-primary" onClick={handleResetCards}>
                                    <h6 className="center-text">Clear Votes</h6>
                                </button>
                            </div>

                                <button className="finish-voting btn btn-primary" onClick={handleFinish}>
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
                        {props.cards.cards.map((card) => (
                            <ControllSetting value={card.value}/>
                        ))}
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
