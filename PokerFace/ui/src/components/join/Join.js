import './Join.css'
import {Link} from "react-router-dom";
function Join(){
    return(
        <body className="center">
        <div className="register">
            <div className="info">
                <h2>Let's start!</h2>
                <h3>Join the room:</h3>
            </div>
            <input type="text" placeholder="  &#61447;   Enter your name"></input>
                <a href="#" className="button">
                    <h3 className="join-button">Enter</h3>
                </a>
                <div className="login">
                    <h3 className="left-line">—————— </h3>
                    <h3 className="middle-text"> Already have an account? </h3>
                    <h3 className="right-line"> ——————</h3>
                </div>
            <Link to="/Login" className="button">
                <h3 className="login-button">Login</h3>
            </Link>
        </div>
        </body>
    );
}
export default Join;