import "./Poker.css";
import UserIcon from "../../imgs/user.png";
import { useEffect, useState } from "react";
import GetSessionUsers from "../../api/getSessionUsers";
import poker from "./Poker";
const PlayerList = (props) => {
  const [users, setUsers] = useState([]);
  const [userData, setUserData] = useState({});


  useEffect( () => {
    setUserData(props.userData);
    console.log("User data props: ", props);
    const GetData = async () => {
      let response = await GetSessionUsers({
        id: props.userData.roomId,
      });
      if (response) {
        console.log(response);
        setUsers(response);
      }
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
