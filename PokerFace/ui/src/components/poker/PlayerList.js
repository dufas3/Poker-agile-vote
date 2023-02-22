import "./Poker.css";
import UserIcon from "../../imgs/user.png";
import { useEffect, useState } from "react";
function PlayerList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    //get all users api
    fetch("https://localhost:5001/api/user/getall")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setUsers(data);
      });
  }, []);

  return (
    <div className="player-list border rounded bg-light">
      <h1>Player list</h1>
      {users.map((user) => (
        <h4>{user.name}</h4>
      ))}
    </div>
  );
}

export default PlayerList;
