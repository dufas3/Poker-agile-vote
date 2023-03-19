import { useState } from "react";
import "./Card.css";
import setUserSelectedCard from "../../api/set/setUserSelectedCard";

const Card = ({ cardId, cardValue, userId, sessionState, roomId }) => {
  const [isSelected, setIsSelected] = useState(false);

  const handleOnClick = async () => {
    console.log("handle set active card invoked!");
    console.log("sessionState", sessionState);
    if (sessionState == 1) return;

    try {
      let isSelectedBgs = document.querySelectorAll(".selected-bg-true");
      let isSelectedTxts = document.querySelectorAll(".selected-true");

      isSelectedBgs.forEach((isSelectedBg) => {
        isSelectedBg.classList.remove("selected-bg-true");
      });
      isSelectedTxts.forEach((isSelectedTxt) => {
        isSelectedTxt.classList.remove("selected-true");
      });
    } catch (error) {}

    setTimeout(
      function () {
        if (!isSelected) {
          setIsSelected(true);
        } else {
          setIsSelected(false);
        }
      }.bind(this),
      55
    );

    let response = await setUserSelectedCard({
      userId: userId,
      cardId: cardId,
      roomId: roomId,
    });
    console.log("setUserSelectedCard response", response);
  };

  return (
    <div className={!isSelected ? "card-css" : "card-css selected-bg-true"}>
      {cardValue.length > 3 ? (
        <button className="card-button" onClick={handleOnClick}>
          <h6
            className={!isSelected ? "number-top" : "number-top selected-true"}
          >
            {cardValue}
          </h6>

          <div
            className={
              !isSelected ? "card-middle" : "card-middle selected-bg-true"
            }
          >
            <h6 className={!isSelected ? "number" : "number selected-true"}>
              {cardValue}
            </h6>
          </div>
          <h6
            className={
              !isSelected ? "number-bottom" : "number-bottom selected-true"
            }
          >
            {cardValue}
          </h6>
        </button>
      ) : (
        <button className="card-button" onClick={handleOnClick}>
          <h5
            className={
              !isSelected ? "number-top1" : "number-top1 selected-true"
            }
          >
            {cardValue}
          </h5>
          <div
            className={
              !isSelected ? "card-middle" : "card-middle selected-bg-true"
            }
          >
            <h2 className={!isSelected ? "number" : "number selected-true"}>
              {cardValue}
            </h2>
          </div>
          <h5
            className={
              !isSelected ? "number-bottom1 " : "number-bottom1 selected-true"
            }
          >
            {cardValue}
          </h5>
        </button>
      )}
    </div>
  );
};
export default Card;
