import Nav from "../header/Nav";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import {useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import GetCards from "../../api/get/getCards";
import getSessionState from "../../api/get/getSessionState";
import GetActiveCards from "../../api/get/getActiveCards";
import GetSessionUsers from "../../api/get/getSessionUsers";
import {MethodNames} from "../../common/methodNames";
import {signalRConnection} from "../../api/signalR/signalRHub";
import getSession from "../../api/get/getSession";
import {useSearchParams} from "react-router-dom";
import GetSession from "../../api/get/getSession";

const Poker = () => {

    const [cards, setCards] = useState([]);
    const [role, setRole] = useState("");
    const [users, setUsers] = useState([]);
    const [user, setUser] = useState({name: "", role: ""});
    const [sessionState, setSessionState] = useState(0);
    const [activeCards, setActiveCards] = useState([]);
    const [roomId, setRoomId] = useState("")
    const location = useLocation();
    const [searchParams] = useSearchParams();

    useEffect(() => {
        const setData = () => {
            if (location.state)
                setUser({
                    name: location.state.name,
                    role: location.state.role,
                });
        };
        setData();
    }, []);

    useEffect(() => {
        console.log("searchParams ", searchParams.get("room"))
        const setData = async () => {
            if (!signalRConnection) await signalRConnection.start();

            let response = await GetSession({roomId: searchParams.get("room")});
            console.log("responseresponseresponse: ", response)
            setCards(response.cards);
            setRoomId(response.roomId)
            setSessionState(response.state)
            await getCards();

        };
        setData();
    }, []);
    useEffect(() => {
        setRoomId(searchParams.get("room"))
    }, [roomId])

    const setUserList = async () => {
        let response = await GetSessionUsers({
            id: searchParams.get("room"),
        });
        if (response) setUsers(response);
    };

    const setSessionStateFromApi = async () => {
        let response = await getSessionState({roomId: searchParams.get("room")});
        setSessionState(response);
    };

    const getCards = async () => {
        let response = await GetCards({
            roomId: searchParams.get("room"),
        });
        if (response) {
            setCards(response);
        }
    };

    const getActiveCards = async () => {
        let activeCardsResponse = await GetActiveCards({
            roomId: searchParams.get("room"),
        });
        if (activeCardsResponse) {
            setActiveCards(activeCardsResponse);
        }
    };

    useEffect(() => {
        console.log("setting users");
        setUserList();
    }, []);

    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.PlayerListUpdate, () => {
                setUserList();
            });
        };
        getData();
    }, [users]);

    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.SessionStateUpdate, () => {
                setSessionStateFromApi();
            });
        };
        getData();
    }, [sessionState]);

    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.ActiveCardsUpdate, () => {
                getActiveCards();
            });
        };
        getData();
    }, [activeCards]);


    return (
        <>
            {roomId && (
                <>
                    <Nav/>
                    <div className="poker">
                        <div className="voting">
                            <VotingArea
                                cards={activeCards}

                                userId={localStorage.getItem("userId")}
                                roomId={searchParams.get("room")}
                                sessionState={sessionState}
                                userList={users}
                            />
                            {user.role == "moderator"? <VotingControls
                                cards={cards}
                                roomId={searchParams.get("room")}
                                activeCards={activeCards}
                                session={sessionState}
                            /> :
                            ""}
                        </div>
                        <PlayerList sessionState={sessionState} userList={users}/>
                    </div>
                </>
            )}
        </>
    );
};

export default Poker;
