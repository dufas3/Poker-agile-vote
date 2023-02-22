import FestoLogo from '../../imgs/festo.png';
import Dropdown from "react-bootstrap/Dropdown";
import { Link } from "react-router-dom";
import './Nav.css'
import { useEffect, useState } from "react";

const Nav = () => {

    const [userName, setUserName] = useState(undefined);
    useEffect(() => {
        setUserName(localStorage.getItem("name"))
    }, [localStorage.getItem("name")]);

    const HandleLogout = () => {
        localStorage.removeItem("name");
    }

    return (
        <header>
            <nav class="navbar navbar-expand-sm navbar-light bg-light border-bottom box-shadow mb-3 fixed-top">
                <div class="container-fluid">
                    <a class="navbar-brand">Festo Scrum Poker</a>
                    {!userName ?
                        <Link to="/Login">
                            <a className="btn" id="H1"><i className="bi bi-person-fill" />Login</a>
                        </Link> :
                        <div className="component">
                            <Dropdown>
                                <Dropdown.Toggle variant="btn-secondary" id="dropdown-basic">
                                    <i className="bi bi-person-fill" /> {userName}
                                </Dropdown.Toggle>

                                <Dropdown.Menu>
                                    <Dropdown.Item>
                                        <Link to="/">
                                            <a onClick={() => HandleLogout()} className="btn" id="logoutbutton">
                                                <i className="fa-solid fa-arrow-right-from-bracket" />Logout</a>
                                        </Link>
                                    </Dropdown.Item>
                                </Dropdown.Menu>
                            </Dropdown>
                        </div>
                    }
                    <a class="navbar-brand" href="https://www.festo.com/us/en/" target="_blank" id="logolink">
                        <img src={FestoLogo} className="logo" />
                    </a>
                </div>
            </nav>
        </header>
    )
}
export default Nav;