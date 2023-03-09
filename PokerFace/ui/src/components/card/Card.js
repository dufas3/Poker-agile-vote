import {useEffect, useState} from "react";
import './Card.css'
import getSessionState from "../../api/get/getSessionState";
import setUserSelectedCard from "../../api/set/setUserSelectedCard";
import {useLocation} from "react-router-dom";
import getUser from "../../api/get/getUser";

const Card = (props) => {

        const [isSelected, setIsSelected] = useState(false);
        const [user, setUser] = useState({})

    useEffect(()=>{
        const setUpUser = async ()=>{
            let response = await getUser({userId: localStorage.getItem("userId")})
            setUser(response)
        }
        setUpUser();
    },[])



        const selectedCard = async () => {
            let sessionState = await getSessionState({roomId: props.cardValue.roomId})
            if (sessionState == 1) return;

            try {
                let isSelectedBgs = document.querySelectorAll('.selected-bg-true');
                let isSelectedTxts = document.querySelectorAll('.selected-true');

                isSelectedBgs.forEach((isSelectedBg) => {
                    isSelectedBg.classList.remove('selected-bg-true');
                })
                isSelectedTxts.forEach((isSelectedTxt) => {
                    isSelectedTxt.classList.remove('selected-true');
                })
            } catch (error) {
            }

            setTimeout(function () {
                if (!isSelected) {
                    setIsSelected(true);
                } else {
                    setIsSelected(false);
                }
            }.bind(this), 55)

            await setUserSelectedCard({userId: user.id, cardId: props.cardValue.cardId});
        }

        return (
            <div className={!isSelected ? "card-css" : "card-css selected-bg-true"}>
                {props.cardValue.cardValue.length > 3 ? <button className="card-button" onClick={selectedCard}>

                        <h6 className={!isSelected ? "number-top" : "number-top selected-true"}>{props.cardValue.cardValue}</h6>

                        <div className={!isSelected ? "card-middle" : "card-middle selected-bg-true"}>
                            <h6 className={!isSelected ? "number" : "number selected-true"}>{props.cardValue.cardValue}</h6>
                        </div>
                        <h6 className={!isSelected ? "number-bottom" : "number-bottom selected-true"}>{props.cardValue.cardValue}</h6>
                    </button> :
                    <button className="card-button" onClick={selectedCard}><h5
                        className={!isSelected ? "number-top1" : "number-top1 selected-true"}>{props.cardValue.cardValue}</h5>
                        <div className={!isSelected ? "card-middle" : "card-middle selected-bg-true"}>
                            <h2 className={!isSelected ? "number" : "number selected-true"}>{props.cardValue.cardValue}</h2>
                        </div>
                        <h5 className={!isSelected ? "number-bottom1 " : "number-bottom1 selected-true"}>{props.cardValue.cardValue}</h5>
                    </button>}
            </div>
        );
    }
;

export default Card;
