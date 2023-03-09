import './PlayerList.css'
import '../GlobalCSS.css'
import "react-toastify/dist/ReactToastify.css";
import UserIcon from "../../imgs/user.png";
import VoteIcon from "../../imgs/vote-icon.png";
import Notifications from "../notifications/Notifications";
import {useEffect, useState} from "react";

const PlayerList = ({sessionState, userList}) => {
    const [users, setUsers] = useState([]);
    const [state, setSessionState] = useState(0);
    const [userCount, setUserCount] = useState(0);
    const [listText, setListText] = useState("");

    const setUserList = async () => {
        setUserCount(userList.length - 1);
    };

    useEffect(() => {
        setUserList();
        setSessionState(sessionState);
    }, [userList, sessionState]);

    useEffect(() => {
        if (userCount <= 0) {
            setListText("Waiting for players");
        } else {
            if (state == 0) {
                setListText("Waiting on " + userCount + " players to vote");
            } else if (state == 2) {
                setListText("Waiting on " + userCount + " players to vote");
            } else if (state == 1) {
                setListText("Waiting for moderator to finalise votes");
            }
        }

        setUsers(userList)
    }, [state, userList])

    useEffect(() => {
        let allVoted = true;

        if (userList.length > 1) {
            userList.map((user) => {
                if (!user.name.includes("@gmail.com")) {
                    if (user.selectedCard == null) {
                        allVoted = false;

                    }
                }
            })
        } else {
            allVoted = false
        }
        if (allVoted) {
            //  To Do (change state to SessionState.FINILIZESTATE cuz all voted)
        }
    }, [userList])


    return (
        <div className="player-list border rounded bg-light">
            <h6 className="bg-primary border border-primary rounded-top w-100 p-4 text-center text-white">
                {listText}
            </h6>
            {users.map((user) => (
                <div className="mt-2  border-top border-bottom user align-center-between">
                    <div className="align-center-start">
                        <div className="icon m-lg-1 align-center">
                            {user.name.includes("@gmail.com")
                                ? <><img src={UserIcon} className="user-icon"/><i
                                    className="fa-solid fa-circle moderator-indicator"></i></> :
                                <><img src={UserIcon} className="user-icon"/><i
                                    className="fa-solid fa-circle user-indicator"></i></>}
                        </div>
                        <h6>
                            {user.name}
                            {user.cardId != "0"
                                ? user.name.includes("@gmail.com")
                                    ? ""
                                    : user.selectedCard && (
                                    <i className="fa-regular fa-circle-check text-primary"></i>
                                )
                                : ""}
                        </h6>
                    </div>
                    {user.name.includes("@gmail.com") ? (
                        ""
                    ) : state == 1 ? (
                        <h5
                            className={
                                user.selectedCard == null
                                    ? "vote-icon"
                                    : user.selectedCard.value.length > 3
                                        ? "vote-icon out-of-border"
                                        : "vote-icon"
                            }
                        >
                            {user.selectedCard == null ? "?" : user.selectedCard.value}
                        </h5>
                    ) : (
                        <img src={VoteIcon} className="vote-icon"/>
                    )}
                </div>
            ))}
            <Notifications data={{state: sessionState}}/>
        </div>
    );
};

export default PlayerList;
