import './Login.css'
import {Link} from "react-router-dom";
import Nav from "../header/Nav";
import {useEffect, useState} from "react";

function Login() {


    localStorage.removeItem("name");
    localStorage.removeItem("role");

    const testEmail = "testemail@gmail.com";
    const testPassword = "testpassword123";

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [enter, setEnter] = useState(false);
    const [errors, setErrors] = useState(false);

    useEffect(() => {
        if (!email || !password) {
            setEnter(false);
        } else if (email.length == 0 || password.length == 0) {
            setEnter(false);
        } else if (email == testEmail && password == testPassword) {
            setEnter(true);
        } else {
            setEnter(false);
        }
    }, [email, password])

    const validation = () => {
        if (!email || !password) {
            setErrors(true);
        } else if (email.length == 0 || password.length == 0) {
            setErrors(true);
        } else if (email == testEmail && password == testPassword) {
            setErrors(false);
            localStorage.setItem("name", email);
            localStorage.setItem("role", "moderator");
        } else {
            setErrors(true);
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
                       placeholder="  &#61447;   Enter your email" id="emailenter"></input>

                <input onChange={(e) => {
                    setPassword(e.currentTarget.value)
                }} className={errors ? "border-danger" : ""} type="password"
                       placeholder="  &#61475;   Enter your password" id="passwordenter"></input>

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