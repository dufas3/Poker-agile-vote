import './Login.css'
import {Link} from "react-router-dom";

function Login() {
    return (
        <body className="center">
        <div className="login">
            <div className="info">
                <h2>Welcome back</h2>
                <h3>Login with your account:</h3>
            </div>
            <input type="email" placeholder="  &#61447;   Enter your email"></input>
            <input type="password" placeholder="  &#61475;   Enter your password"></input>
            <a href="#" className="button">
                <h4 className="reset-button">Forgotten password?</h4>
            </a>
            <a href="#" className="button">
                <h3 className="login-button">Login</h3>
            </a>
            <Link to="/Join" className="button">
                <h4 className="reset-button">Don't have an account?</h4>
            </Link>
        </div>
        </body>
    );
}

export default Login;