import "./Poker.css";
import UserIcon from "../../imgs/user.png";
import { useEffect, useState } from "react";
import GetSessionUsers from "../../api/getSessionUsers";
const PlayerList = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const GetData = async () => {
      let response = await GetSessionUsers({
        id: localStorage.getItem("roomId"),
      });
      if (response) setUsers(response);
    };
    GetData();
    return () => {};
  }, []);

  return (
    <div className="player-list border rounded bg-light">
      <h1>Player list</h1>
      {users.map((user) => (
        <h4>{user.name}</h4>
      ))}
    </div>
  );
};

export default PlayerList;
