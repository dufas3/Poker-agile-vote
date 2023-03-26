import Nav from "../header/Nav";
import "../GlobalCSS.css";
import PlayerList from "./PlayerList";
import VotingArea from "./VotingArea";
import VotingControls from "./VotingControls";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import GetCards from "../../api/get/getCards";
import getSessionState from "../../api/get/getSessionState";
import GetActiveCards from "../../api/get/getActiveCards";
import GetSessionUsers from "../../api/get/getSessionUsers";
import {MethodNames} from "../../common/methodNames";
import {signalRConnection} from "../../api/signalR/signalRHub";
import {useSearchParams} from "react-router-dom";
import getUser from "../../api/get/getUser";
import {getUserId, setUserId} from "../../common/UserId";
import setSessionState from "../../api/set/setSessionState";
import {SessionState} from "../../common/sessionState";
import getSettings from "../../api/get/getSettings";

const Poker = () => {
    const [cards, setCards] = useState([]);
    const [users, setUsers] = useState([]);
    const [user, setUser] = useState([]);
    const [state, setState] = useState(0);
    const [activeCards, setActiveCards] = useState([]);
    const [roomId, setRoomId] = useState("");
    const [searchParams] = useSearchParams();
    const [navig, setNavig] = useState();
    const [settings, setSettings] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        const setData = async () => {
            if (!signalRConnection) await signalRConnection.start();

            //api calls
            await updateSettings();
            await setUpUser();
            await getCards();
            await setUserList();
            await setSessionStateFromApi();
            await getActiveCards();
        };
        setData();
    }, []);

    useEffect(() => {
        setRoomId(searchParams.get("roomId"));
    }, [roomId]);
    useEffect(() => {
        if (getUserId() == null) {
            if (searchParams.get("roomId") === undefined) {
                setNavig(navigate("/Login", {replace: true}));
            } else {
                setNavig(
                    navigate("/Join?roomId=" + searchParams.get("roomId"), {
                        replace: true,
                    })
                );
            }
        }
    }, []);

    useEffect(() => {
        //to force rerender when response is awaited
    }, [users]);

    const setUserList = async () => {
        let response = await GetSessionUsers({
            roomId: searchParams.get("roomId"),
        });
        console.log("GetSessionUsers response", response);
        if (response) setUsers(response);
    };

    const setSessionStateFromApi = async () => {
        let response = await getSessionState({
            roomId: searchParams.get("roomId"),
        });
        console.log("setSessionStateFromApi response", response);
        setState(response);
    };

    const setUpUser = async () => {
        let response = await getUser({
            userId: getUserId(),
            roomId: searchParams.get("roomId"),
        });
        console.log("getUser response", response);
        setUser(response);
    };

    const getCards = async () => {
        let response = await GetCards();
        console.log("GetCards response", response);
        if (response) {
            setCards(response);
        }
    };

    const getActiveCards = async () => {
        let activeCardsResponse = await GetActiveCards({
            roomId: searchParams.get("roomId"),
        });
        console.log("GetActiveCards response", activeCardsResponse);
        if (activeCardsResponse) {
            setActiveCards(activeCardsResponse);
        }
    };
    const updateSettings = async () => {
        let response = await getSettings({roomId: searchParams.get("roomId")});
        console.log("aaaaa ",response);
        setSettings(response);
    }
    //---------------CHANGES STATE IF ALL PLAYERS VOTED---------------------
    useEffect(() => {
        let allVoted = true;

        if (users.length > 1) {
            users.map((user) => {

                if (!user.name.includes("@")) {
                    if (user.selectedCard == null) {
                        allVoted = false;
                    }
                }
            })
        } else {
            allVoted = false
        }
        if (allVoted) {
            const stateChange = async () => {
                await setSessionState({
                    roomId: searchParams.get("roomId"),
                    state: SessionState.FINILIZESTATE,
                });
                let response = await getSessionState({
                    roomId: searchParams.get("roomId"),
                });
                console.log("getSessionState: ", response)
            }
            stateChange();
        }
    }, [users])

    //-----------------Event Listeners-------------------------------------
    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.PlayerListUpdate, () => {
                console.log("PlayerListUpdate Event!");
                setUserList();
            });
        };
        getData();
    }, []);
    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.SessionStateUpdate, () => {
                console.log("SessionStateUpdate Event!");
                setSessionStateFromApi();
            });
        };
        getData();
    }, []);

    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.ActiveCardsUpdate, () => {
                console.log("ActiveCardsUpdate Event!");
                getActiveCards();
            });
        };
        getData();
    }, []);

    useEffect(() => {
        const getData = async () => {
            signalRConnection.on(MethodNames.SessionLogout, () => {
                console.log("SessionLogout Event!");
                setUserId(undefined);
                setNavig(navigate("/Login", {replace: true}));
            });
        };
        getData();
    }, []);
    useEffect(() => {
        const getDate = async () => {
            signalRConnection.on(MethodNames.SettingsUpdate, () => {
                console.log("Settings update!");
                updateSettings();
            });
        }
        getDate();
    }, [])
//---------------------------------------------------------------------------
    return (
        <>
            {roomId && (
                <>
                    <Nav user={user}/>
                    <div className="poker">
                        <div className="voting">
                            <VotingArea
                                cards={activeCards}
                                userId={user.id}
                                roomId={searchParams.get("roomId")}
                                sessionState={state}
                                userList={users}
                            />
                            {user.role == "moderator" ? (
                                <VotingControls
                                    settings={settings}
                                    cards={cards}
                                    roomId={searchParams.get("roomId")}
                                    activeCards={activeCards}
                                    session={state}
                                    key={activeCards.join(",")}
                                />
                            ) : (
                                ""
                            )}
                        </div>
                        <PlayerList sessionState={state} userList={users}/>
                    </div>
                </>
            )}
        </>
    );
};

export default Poker;
