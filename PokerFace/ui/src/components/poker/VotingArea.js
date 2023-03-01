import "./Poker.css";
import Card from "../card/Card";
import { useState, useEffect } from "react";
import GetCards from "../../api/getCards";

const VotingArea = (props) => {
  return (
    <div className="voting-area border rounded bg-light">
      <div className="voting-area">
        <div className="row-1">
          {props.cards.map((card) => (
            <Card key={card.id} cardValue={card.value} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default VotingArea;
