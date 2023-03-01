import "./Poker.css";
import UserIcon from "../../imgs/user.png";
import {useEffect, useState} from "react";
import GetSessionUsers from "../../api/getSessionUsers";
import VoteIcon from '../../imgs/vote-icon.png';
import poker from "./Poker";

const PlayerList = (props) => {
    const [users, setUsers] = useState([]);
    const [userData, setUserData] = useState({});
    const [voted, setVoted] = useState(true);


    useEffect(() => {
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
        return () => {
        };
    }, []);

    return (
        <div className="player-list border rounded bg-light">
            <h4 className="bg-primary border border-primary rounded-top w-100 p-4 text-center text-white">Player
                list</h4>
            {users.map((user) => (
                <div className="mt-2  border-top border-bottom user align-center-between">
                    <div className="align-center-start">
                        <div className="icon m-lg-1 align-center">
                            <img src={UserIcon} className="user-icon"/>
                        </div>
                        <h6>{user.name} {user.cardId != "0" ? user.name.includes("@gmail.com") ? "" :
                            <i className="fa-regular fa-circle-check text-primary"></i> : ""}</h6>
                    </div>
                    {user.name.includes("@gmail.com") ? "" : <img src={VoteIcon} className="vote-icon"/>}

                </div>
            ))}
        </div>
    );
};

export default PlayerList;
