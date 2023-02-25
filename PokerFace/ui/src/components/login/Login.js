import './Login.css'
import {Link} from "react-router-dom";
import Nav from "../header/Nav";
import {useEffect, useState} from "react";
import getModerator from "../../api/getModerator";

function Login() {

    localStorage.removeItem("name");
    localStorage.removeItem("role");

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [roomId, setRoomId] = useState('');
    const [enter, setEnter] = useState(false);
    const [errors, setErrors] = useState(false);

    let generatedId = (Math.floor(Math.random() * 10000) + 10000).toString().substring(1);
    setRoomId(generatedId);
    console.log(roomId);

    const validation = async () => {
        let response = await getModerator(email, password);

        if (response['Id'] == undefined) {
            setEnter(false);
        } else {
            setErrors(false);
            setEnter(true);
        }

        if (enter == false) {
            setErrors(true);
        } else {
            setErrors(false);
        }
    }
    return (
        <>
            <Nav/>
            <body className="center">
            <div className="login">
                <div className="info">
                    <h2>Welcome back</h2>
                    <h3>Login with your account:</h3>
                </div>

                <input onChange={(e) => {
                    setEmail(e.currentTarget.value)
                }} className={errors ? "border-danger" : ""} type="email"
                       placeholder="&#61447;   Enter your email" id="emailenter"></input>

                <input onChange={(e) => {
                    setPassword(e.currentTarget.value)
                }} className={errors ? "border-danger" : ""} type="password"
                       placeholder="&#61475;   Enter your password" id="passwordenter"></input>

                <div className="error">{errors &&
                    <h5 className="error-text text-danger">Wrong username or password!</h5>}
                </div>

                <button onClick={() => {
                    validation()
                }} className="fix" id="loginbutton">
                    {enter ? <Link to="/Poker">
                        <h3 className="login-button">Login</h3>
                    </Link> : <Link to="/Login">
                        <h3 className="login-button">Login</h3>
                    </Link>
                    }
                </button>
                <div className="help">
                    <a href="#" className="button" id="forgotpasswordbutton">
                        <h4 className="reset-button">Forgotten password?</h4>
                    </a>

                    <Link to="/" className="button" id="registerbutton">
                        <h4 className="reset-button">Don't have an account?</h4>
                    </Link>
                </div>
            </div>
            </body>
        </>
    );
}

export default Login;