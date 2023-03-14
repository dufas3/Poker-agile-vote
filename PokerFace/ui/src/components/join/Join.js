import "./Join.css";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { useCallback, useEffect, useState } from "react";
import Nav from "../header/Nav";
import addToSession from "../../api/addToSession";
import LoadingScreen from "../loadingScreen/LoadingScreen";
import GetRoom from "../../api/get/getRoom";
import { MethodNames } from "../../common/methodNames";
import { signalRConnection } from "../../api/signalR/signalRHub";
import * as signalR from "@microsoft/signalr";
import { setUserId } from "../../common/UserId";
import GetSessionUsers from "../../api/get/getSessionUsers";

const Join = () => {
  const [name, setName] = useState("");
  const [roomId, setId] = useState("");
  const [enter, setEnter] = useState(false);
  const [errors, setErrors] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [navig, setNavig] = useState();
  const navigate = useNavigate();
  const handleOnClick = useCallback(() => navig, [navigate]);

  const [searchParams] = useSearchParams();

  useEffect(() => {
    if (searchParams.get("room") == undefined) {
      setNavig(navigate("/Login", { replace: true }));
    } else {
      setId(searchParams.get("room"));
    }
  }, []);

  const validation = async () => {
    let isUsernameTaken = false;
    let sessionUserList = await GetSessionUsers({
      id: searchParams.get("room"),
    });

    sessionUserList.map((sessionUser) => {
      if (sessionUser.name == name) {
        isUsernameTaken = true;
      }
    });
    setIsLoading(true);
    if (!name) {
      setErrorMessage("Please enter username!");
      setErrors(true);
      setIsLoading(false);
    } else if (name.replaceAll(" ", "").length == 0) {
      setErrorMessage("Please enter username!");
      setErrors(true);
      setIsLoading(false);
    } else if (name.includes("@")) {
      setErrorMessage("You can't use @ in your username!");
      setErrors(true);
      setIsLoading(false);
    } else if (sessionUserList.length >= 9) {
      setErrorMessage("This room is full!");
      setErrors(true);
      setIsLoading(false);
    } else if (name.length <= 2) {
      setErrorMessage("Username is too short!");
      setErrors(true);
      setIsLoading(false);
    } else if (isUsernameTaken) {
      setErrorMessage("This username is taken!");
      setErrors(true);
      setIsLoading(false);
    } else {
      setErrors(false);
      let roomExist = await GetRoom({ roomId: roomId });

      if (!roomExist) {
        setErrors(true);
        return;
      }

      if (signalRConnection.state === signalR.HubConnectionState.Connected) {
        await signalRConnection.stop();
      }
      await signalRConnection.start();
      await signalRConnection.invoke(MethodNames.ReceiveConnectSockets, "");

      let response = await addToSession({
        name: name,
        roomId: roomId,
        socketId: signalRConnection.connectionId,
      });

      if (!response) {
        setErrors(true);
      }
      setUserId(response.id);
      setIsLoading(false);
      setNavig(navigate("/Poker?" + searchParams, { replace: true }));
      setEnter(true);
      handleOnClick();
    }
  };

  return (
    <>
      {isLoading ? <LoadingScreen /> : ""}
      <body className="center">
        <div className="register">
          <div className="info">
            <h2>Let's start!</h2>
            <h3>Join the room:</h3>
          </div>
          <input
            onChange={(e) => {
              setName(e.currentTarget.value);
            }}
            type="text"
            placeholder="&#61447;   Enter your name"
            className={errors ? "border-danger" : ""}
            minLength="2"
            maxLength="25"
            id="loginname"
          ></input>

          <div className="error">
            {errors && (
              <h5 className="error-text text-danger">{errorMessage}</h5>
            )}
          </div>

          <button
            onClick={() => {
              validation();
            }}
            className="join-button"
            id="joinbutton"
          >
            {enter ? (
              <Link
                to={"/Poker?room=" + searchParams.get("room")}
                style={{ textDecoration: "none" }}
              >
                <h3>Enter</h3>
              </Link>
            ) : (
              <Link
                to={"/Join?room=" + searchParams.get("room")}
                style={{ textDecoration: "none" }}
              >
                <h3>Enter</h3>
              </Link>
            )}
          </button>

          <div className="login-text">
            <h3 className="left-line">—————— </h3>
            <h3 className="middle-text"> Already have an account? </h3>
            <h3 className="right-line"> ——————</h3>
          </div>
          <Link to="/Login" className="button" id="loginbutton">
            <h3 className="login-button">Login</h3>
          </Link>
        </div>
      </body>
    </>
  );
};

export default Join;
