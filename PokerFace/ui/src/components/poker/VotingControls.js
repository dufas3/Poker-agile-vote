import "./Poker.css";
import {useEffect, useState} from "react";
import {SessionState} from "../../common/sessionState";
import setSessionState from "../../api/set/setSessionState";
import ClearSessionVotes from "../../api/clearSessionVotes";
import setActiveCards from "../../api/set/setActiveCards";

function VotingControls({cards, activeCards, roomId}) {
    const [inSettings, setInSettings] = useState(false);
    let sessionActiveCards = [];
    let inputValuesObj = [{}];

    useEffect(() => {
        sessionActiveCards = activeCards;
    }, [activeCards]);

    useEffect(() => {
        console.log("cards: ", cards)
        console.log("activeCards: ", activeCards)
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
        await ClearSessionVotes({roomId: roomId});
    };
    const handleResetCards = async () => {
        await setSessionState({
            roomId: roomId,
            state: SessionState.VOTESTATE,
        });
        await ClearSessionVotes({roomId: roomId});
    };

    const handleChangeCardsSave = async () => {
        console.log("inputValuesObj: ", inputValuesObj)
        getAllEnabledCardsIdArray();
            setInSettings(false);
            await setActiveCards({
                roomId: roomId,
                cards: sessionActiveCards,
            });
            await ClearSessionVotes({roomId: roomId});
    };
    const handleChangeCardsCancel = async () => {
        if (inSettings) {
            setInSettings(false);
        } else {
            setInSettings(true);
        }
    };

    useEffect(()=>{
        setupCardAvailability();
    },[inputValuesObj])


    const setupCardAvailability = () => {
        let bool;
        cards.map((card) => {
            if(sessionActiveCards.includes(card)){
                bool = true;
            } else{
                bool = false;
            }
            inputValuesObj.push({cardId: card.id, value: card.value, enabled: bool})
        })
        console.log("inputValuesObj: ", inputValuesObj)
    }





    const getSingleCardAvailability = ({card}) => {
        let isAvailable;
        inputValuesObj.map((cardObj) => {
            if (cardObj != undefined) {
                if (cardObj.cardId == card.id) {
                    isAvailable = cardObj.enabled;
                }
            }
        })
        return isAvailable
    }


    const setSingleCardAvailability = ({card}) => {

        const index = inputValuesObj.findIndex(obj => obj.cardId === card.id);

        let bool = getSingleCardAvailability(card = {card})? false : true;

        const updatedObj = {cardId: card.id, value: card.value, enabled: bool};
        console.log("updatedObj: ", updatedObj)
        inputValuesObj[index] = updatedObj;

    }

    const getAllEnabledCardsIdArray = () => {
        inputValuesObj.map((card)=>{
            if(card.enabled){
                sessionActiveCards.push({id: card.cardId, value: card.value, isActive: true})
            }
        })
    }


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
                                    onChange={()=>{
                                        setSingleCardAvailability({card: card})
                                    }}
                                    className="form-check-input"
                                    type="checkbox"
                                    checked={getSingleCardAvailability({card: card}) ? "true" : "false"}
                                    value="true"
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
}

export default VotingControls;
