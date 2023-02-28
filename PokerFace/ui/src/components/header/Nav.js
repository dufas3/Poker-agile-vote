import FestoLogo from "../../imgs/festo.png";
import Dropdown from "react-bootstrap/Dropdown";
import {Link, useLocation} from "react-router-dom";
import "./Nav.css";
import { useEffect, useState } from "react";

const Nav = (props) => {
  const [userData, setUserData] = useState({ name: "", roomId: "", role: "" });
  const location = useLocation();

  useEffect(() => {
    const setData = () => {
      if(location.state)
      setUserData({ name: location.state.name, roomId: location.state.roomId, role: location.state.role });
    };
    setData();
  }, []);

  console.log(location.state);

  return (
    <header>
      <nav className="navbar navbar-expand-sm navbar-light bg-light border-bottom box-shadow mb-3 fixed-top">
        <div className="container-fluid">
          <a className="navbar-brand">Festo Scrum Poker</a>
          {!userData.name ? (
            <Link to="/Login">
              <a className="btn" id="H1">
                <i className="bi bi-person-fill" />
                Login
              </a>
            </Link>
          ) : (
            <div className="component">
              <Dropdown>
                <Dropdown.Toggle variant="btn-secondary" id="dropdown-basic">
                  <i className="bi bi-person-fill" /> {userData.name}
                </Dropdown.Toggle>

                <Dropdown.Menu>
                  <Dropdown.Item>
                    <Link to="/" style={{ textDecoration: "none" }}>
                      <a onClick={() => {}} className="btn" id="logoutbutton">
                        <i className="fa-solid fa-arrow-right-from-bracket" />
                        Logout
                      </a>
                    </Link>
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            </div>
          )}
          <a
            className="navbar-brand"
            href="https://www.festo.com/us/en/"
            target="_blank"
            id="logolink"
          >
            <img src={FestoLogo} className="logo" />
          </a>
        </div>
      </nav>
    </header>
  );
};
export default Nav;
