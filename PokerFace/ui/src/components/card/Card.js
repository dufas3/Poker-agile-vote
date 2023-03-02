import setActiveUserCard from "../../api/setActiveUserCard";
import {useState} from "react";
import './Card.css'
import getSessionState from "../../api/getSessionState";
import {SessionState} from "../../common/sessionState";
import setUserSelectedCard from "../../api/setUserSelectedCard";

const Card = (props) => {

    const [apiResponse, setApiResponse] = useState({});
    const [isSelected, setIsSelected] = useState(false);

    const selectedCard = async () => {

        let sessionState = await getSessionState({roomId: props.cardValue.roomId})
        console.log("Session state: ", sessionState)
        console.log("SessionState.VOTESTATE: ", SessionState.VOTESTATE)
        //if (sessionState.sessionState != SessionState.VOTESTATE) return;
        await setUserSelectedCard({userId: props.cardValue.userId, cardId: props.cardValue.cardId});
        if (!isSelected) {
            setIsSelected(true);
        } else {
            setIsSelected(false);
        }
    }



return (
    <div className={!isSelected? "card selected-true" : "card selected-false"}>
        {props.cardValue.cardValue.length > 3 ? <button className="card-button" onClick={selectedCard}>

                <h6 className={!isSelected? "number-top selected-true": "number-top selected-false"}>{props.cardValue.cardValue}</h6>

                <div className={!isSelected? "card-middle selected-true selected-bg-true" : "card-middle selected-false"}>
                    <h6 className={!isSelected? "number selected-true" : "number selected-false"}>{props.cardValue.cardValue}</h6>
                </div>
                <h6 className={!isSelected? "number-bottom selected-true" : "number-bottom selected-false"}>{props.cardValue.cardValue}</h6></button> :

            <button className="card-button" onClick={selectedCard}><h5
                className={!isSelected? "number-top1 selected-true" : "number-top1 selected-false"}>{props.cardValue.cardValue}</h5>
                <div className={!isSelected? "card-middle selected-true selected-bg-true" : "card-middle selected-false"}>
                    <h2 className={!isSelected? "number selected-true" : "number selected-false"}>{props.cardValue.cardValue}</h2>
                </div>
                <h5 className={!isSelected? "number-bottom1 selected-true" : "number-bottom1 selected-false"}>{props.cardValue.cardValue}</h5></button>}
    </div>
);
}
;

export default Card;
