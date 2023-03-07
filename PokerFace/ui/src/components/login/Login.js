import "./Login.css";
import { Link, useNavigate } from "react-router-dom";
import Nav from "../header/Nav";
import { useCallback, useState } from "react";
import GetModerator from "../../api/get/getModerator";
import LoadingScreen from "../loadingScreen/LoadingScreen";
import { MethodNames } from "../../common/methodNames";
import { signalRConnection } from "../../api/signalR/signalRHub";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [enter, setEnter] = useState(false);
  //make it so the useState will have default state as error object array: [{isActiveError: boolean, errorType: string}] //error types will be: networkError, wrongLoginError
  const [errors, setErrors] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [navig, setNavig] = useState();
  const [userData, setUserData] = useState({});

  const navigate = useNavigate();
  const handleOnClick = useCallback(() => navig, [navigate]);

  const validation = async () => {
    setIsLoading(true);

    let response = await GetModerator({ email: email, password: password });
    console.log("response bad", !response);

    if (!response) {
      setEnter(false);
      setErrors(true);
      setIsLoading(false);
      return;
    } else {
      console.log("response data", response);
      setErrors(false);
      setIsLoading(true);

      await signalRConnection.start();
      await signalRConnection.invoke(
        MethodNames.ReceiveConnectSockets,
        response.id.toString()
      );
      setIsLoading(false);
      setEnter(true);
      localStorage.removeItem("userId", response.id);
      localStorage.setItem("userId", response.id);
      const userData = {
        name: email,
        role: "moderator",
        userId: response.id,
      };
      setUserData(userData);
      setNavig(
        navigate("/Poker?room=" + response.roomId, {
          replace: true,
          state: userData,
        })
      );

      handleOnClick();
    }
  };
  return (
    <>
      <div className="center">
        {isLoading ? <LoadingScreen /> : ""}
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
              <Link to="/Poker" style={{ textDecoration: "none" }}>
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
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
