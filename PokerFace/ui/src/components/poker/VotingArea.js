import "./Poker.css";
import Card from "../card/Card";
import { useState, useEffect } from "react";
import GetCards from "../../api/getCards";

const VotingArea = (props) => {

    console.log("Props from VA: ", props)

  return (
    <div className="voting-area border rounded bg-light">
      <div className="voting-area">
        <div className="row-1">
          {props.cards.cards.map((card) => (
            card.id < 8 ? <Card cardValue={{cardValue: card.value, cardId: card.id, userId: props.cards.userId, roomId: props.cards.roomId}} /> : " "
          ))}
        </div>
          <div className="row-2">
              {props.cards.cards.map((card) => (
                  card.id > 7 ? <Card cardValue={{cardValue: card.value, cardId: card.id, userId: props.cards.userId, roomId: props.cards.roomId}} /> : " "
              ))}
          </div>
      </div>
    </div>
  );
};

export default VotingArea;
