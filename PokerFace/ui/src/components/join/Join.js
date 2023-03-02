import './Join.css'
import {Link, useNavigate} from "react-router-dom";
import {useCallback, useEffect, useState} from "react";
import Nav from "../header/Nav";
import addToSession from "../../api/addToSession";

function Join() {

    const [name, setName] = useState('');
    const [roomId, setId] = useState('');
    const [enter, setEnter] = useState(false);
    const [errors, setErrors] = useState(false);
    const [userData, setUserData] = useState({})

    const [navig, setNavig] = useState();

    const navigate = useNavigate();
    const handleOnClick = useCallback(() => navig, [navigate]);

    const validation = async () => {
        let response = await addToSession({name: name, roomId: roomId});
        if (!response) {
            setErrors(true);
            return;
        }
        if (!name || !roomId) {
            setErrors(true);
        } else if (name.replaceAll(" ", "").length == 0) {
            setErrors(true);
        } else {
            setErrors(false);
            const userData = {
                name: name,
                roomId: roomId,
                role: "player",
                userId: response.id,
            };

            setUserData(userData)
            setNavig(navigate("/Poker", {replace: true, state: userData}));

            handleOnClick();

        }

    }


    return (
        <>
            <Nav/>
            <body className="center">
            <div className="register">
                <div className="info">
                    <h2>Let's start!</h2>
                    <h3>Join the room:</h3>
                </div>
                <input onChange={(e) => {
                    setName(e.currentTarget.value)
                }} type="text" placeholder="&#61447;   Enter your name"
                       className={errors ? "border-danger" : ""}
                       minLength="2" maxLength="25" length id="loginname"></input>
                <input onChange={(e) => {
                    setId(e.currentTarget.value)
                }} type="number" placeholder="   Enter room ID"
                       className={errors ? "border-danger" : ""}
                       minLength="2" maxLength="4" length id="loginid"></input>

                <div className="error">{errors &&
                    <h5 className="error-text text-danger">Please enter username!</h5>}
                </div>

                <button onClick={() => {
                    validation()
                }} className="join-button" id="joinbutton">
                    {enter ? <Link to="/Poker" style={{textDecoration: 'none'}}>
                        <h3>Enter</h3>
                    </Link> : <Link to="/" style={{textDecoration: 'none'}}>
                        <h3>Enter</h3>
                    </Link>
                    }
                </button>

                <div className="login-text">
                    <h3 className="left-line">—————— </h3>
                    <h3 className="middle-text"> Already have an account? </h3>
                    <h3 className="right-line"> ——————</h3>
                </div>
                <Link to="/Login" className="button" id="loginbutton">
                    <h3 className="login-button">Login</h3>
                </Link>
            </div>
            </body>
        </>
    );
}

export default Join;