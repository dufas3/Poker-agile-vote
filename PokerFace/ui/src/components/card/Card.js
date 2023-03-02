import setActiveUserCard from "../../api/setActiveUserCard";
import {useState} from "react";
import './Card.css'

const Card = (props) => {

    const [apiResponse, setApiResponse] = useState({});
    const [isSelected, setIsSelected] = useState(false);

    const selectedCard = async () =>{


        let response = await setActiveUserCard({cardId: props.cardValue.cardId, userId: props.cardValue.userId})
        if(!response) return;
        setApiResponse(response);
        if(!isSelected){
            setIsSelected(true);
        } else{
            setIsSelected(false);
        }


        console.log("selected card is: ", props.cardValue.cardId);
    }

    return (
        <div className={"card-"+isSelected}>
            {props.cardValue.cardValue.length > 3 ? <button className="card-button" onClick={selectedCard}><h6 className={"number-top-"+isSelected}>{props.cardValue.cardValue}</h6>
                    <div className={"card-middle-"+isSelected}>
                        <h6 className={"number-"+isSelected}>{props.cardValue.cardValue}</h6>
                    </div>
                    <h6 className={"number-bottom-"+isSelected}>{props.cardValue.cardValue}</h6></button> :

                <button className="card-button" onClick={selectedCard}><h5 className={"number-top1-"+isSelected}>{props.cardValue.cardValue}</h5>
                    <div className={"card-middle-"+isSelected}>
                        <h2 className={"number-"+isSelected}>{props.cardValue.cardValue}</h2>
                    </div>
                    <h5 className={"number-bottom1-"+isSelected}>{props.cardValue.cardValue}</h5></button>}
        </div>
    );
};

export default Card;
