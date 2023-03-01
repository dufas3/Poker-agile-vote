const Card = (props) => {
  return (
    <div className="card">
      <h5 className="number-top1">{props.cardValue}</h5>
      <div className="card-middle">
        <h2 className="number">{props.cardValue}</h2>
      </div>
      <h5 className="number-bottom1">{props.cardValue}</h5>
    </div>
  );
};

export default Card;
