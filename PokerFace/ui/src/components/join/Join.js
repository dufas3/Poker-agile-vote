import './Join.css'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import Nav from "../header/Nav";

function Join() {

    localStorage.removeItem("name");
    localStorage.removeItem("role");

    const [name, setName] = useState('');
    const [enter, setEnter] = useState(false);
    const [errors, setErrors] = useState(false);

    useEffect(() => {
        if (!name) {
            setEnter(false);
        } else if (name.replaceAll(" ", "").length == 0) {
            setEnter(false);
        } else {
            setEnter(true);
        }
    }, [name])

    const validation = () => {
        if (!name) {
            setErrors(true);
        } else if (name.replaceAll(" ", "").length == 0) {
            setErrors(true);
        } else {
            setErrors(false);
            localStorage.setItem("name", name);
            localStorage.setItem("role", "player");
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
                }} type="text" placeholder="  &#61447;   Enter your name"
                       className={errors ? "border-danger" : ""}
                       minlength="2" maxLength="25" length></input>

                <div className="error">{errors &&
                    <h5 className="error-text text-danger">Please enter username!</h5>}
                </div>

                <button onClick={() => {
                    validation()
                }} className="join-button">
                    {enter ? <Link to="/Poker">
                        <h3>Enter</h3>
                    </Link> : <Link to="/">
                        <h3>Enter</h3>
                    </Link>
                    }
                </button>

                <div className="login-text">
                    <h3 className="left-line">—————— </h3>
                    <h3 className="middle-text"> Already have an account? </h3>
                    <h3 className="right-line"> ——————</h3>
                </div>
                <Link to="/Login" className="button">
                    <h3 className="login-button">Login</h3>
                </Link>
            </div>
            </body>
        </>
    );
}

export default Join;