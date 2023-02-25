import "./Poker.css";
import UserIcon from "../../imgs/user.png";
import {useEffect, useState} from "react";

const PlayerList = (props) => {
    const [users, setUsers] = useState([]);

    useEffect(() => {

        const requestOptions = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({id: props.id})
        };

        fetch('https://localhost:5001/api/session/getsession', requestOptions)
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();

                console.log(data)
            })
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
