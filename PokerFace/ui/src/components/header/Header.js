import './Header.css'
import FestoLogo from '../../imgs/festo.png'
import {Link} from "react-router-dom";
function Header() {
    return(
        <header>
            <Link to="/"><h3 className="text">Festo Scrum Poker</h3></Link>

            <Link to="/Join" className="login-link">
                <h3 className="login">&#61447; Login</h3>
            </Link>

            <a href="src/components/header/Header" className="logo-link">
                <img src={FestoLogo} className="logo"></img>
            </a>
        </header>
    );
}
export default Header;