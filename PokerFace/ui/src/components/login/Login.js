import "./Login.css";
import { Link } from "react-router-dom";
import Nav from "../header/Nav";
import { useEffect, useState } from "react";
import GetModerator from "../../api/getModerator";
import CreateSession from "../../api/createSession";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [enter, setEnter] = useState(false);
  //make it so the useState will have default state as error object array: [{isActiveError: boolean, errorType: string}] //error types will be: networkError, wrongLoginError
  const [errors, setErrors] = useState(false);
  const [responseUser, setResponseUser] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [userData, setUserData] = useState({ name: "", roomId: "", role: "" });

  const validation = async () => {
    setIsLoading(true);
    let response = await GetModerator({ email: email, password: password });

    if (!response) {
      setEnter(false);
      setErrors(true);
      setIsLoading(false);
      return;
    } else {
      setErrors(false);
      setIsLoading(true);

      let generatedId = (Math.floor(Math.random() * 10000) + 10000)
        .toString()
        .substring(1);

      const userData = {
        name: email,
        roomId: generatedId,
        role: "moderator",
      };

      if (!sessionStorage.getItem("roomId")) {
        sessionStorage.setItem("roomId", generatedId);
        await CreateSession({ id: generatedId, moderatorId: response.id });
        setUserData(userData);
        console.log("userdata in login", userData);
        setIsLoading(false);
        setEnter(true);
      }
    }
  };
  return (
    <>
      <Nav />
      <div className="center">
        <div className="login">
          <div className="info">
            <h2>Welcome back</h2>
            <h3>Login with your account:</h3>
          </div>

          <input
            onChange={(e) => {
              setEmail(e.currentTarget.value);
            }}
            className={errors ? "border-danger" : ""}
            type="email"
            placeholder="&#61447;   Enter your email"
            id="emailenter"
          ></input>

          <input
            onChange={(e) => {
              setPassword(e.currentTarget.value);
            }}
            className={errors ? "border-danger" : ""}
            type="password"
            placeholder="&#61475;   Enter your password"
            id="passwordenter"
          ></input>

          <div className="error">
            {errors && (
              <h5 className="error-text text-danger">
                Wrong username or password!
              </h5>
            )}
          </div>

          <button
            onClick={
              isLoading
                ? () => {}
                : () => {
                    validation();
                  }
            }
            className="fix"
            id="loginbutton"
          >
            {enter ? (
              <Link
                to="/Poker"
                state={{
                  name: userData.name,
                  roomId: userData.roomId,
                  role: userData.role,
                }}
                style={{ textDecoration: "none" }}
              >
                <h3 className="login-button">
                  {isLoading ? "Loading" : "Login"}
                </h3>
              </Link>
            ) : (
              <Link to="/Login" style={{ textDecoration: "none" }}>
                <h3 className="login-button">
                  {isLoading ? "Loading" : "Login"}
                </h3>
              </Link>
            )}
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
      </div>
    </>
  );
}

export default Login;
