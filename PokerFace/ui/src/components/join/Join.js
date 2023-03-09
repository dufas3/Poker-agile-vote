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

const Join = () => {
  const [name, setName] = useState("");
  const [roomId, setId] = useState("");
  const [enter, setEnter] = useState(false);
  const [errors, setErrors] = useState(false);
  const [userData, setUserData] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [navig, setNavig] = useState();
  const navigate = useNavigate();
  const handleOnClick = useCallback(() => navig, [navigate]);

  const [searchParams] = useSearchParams();

  useEffect(()=>{
    try{
      localStorage.removeItem("userId");
    }
    catch (error){

    }
  },[])

  useEffect(() => {
    if (searchParams.get("room") == undefined) {
      setNavig(navigate("/Login", { replace: true }));
    } else {
      setId(searchParams.get("room"));
    }
  }, []);

  const validation = async () => {
    if (!name) {
      setErrors(true);
    } else if (name.replaceAll(" ", "").length == 0) {
      setErrors(true);
    } else {
      setErrors(false);
      setIsLoading(true);

      var roomExist = await GetRoom({ roomId: roomId });

      if (!roomExist) {
        setIsLoading(false);
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
        setIsLoading(false);
        setErrors(true);
      }

      const userData = {
        name: name,
        role: "user",
        userId: response.id,
      };
      localStorage.setItem("userId", response.id);

      setUserData(userData);
      setNavig(navigate("/Poker?" + searchParams, { replace: true, state: userData,}));
      setIsLoading(true);
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
              <h5 className="error-text text-danger">Please enter username!</h5>
            )}
          </div>

          <button
            onClick={() => {
              validation();
            }}
            className="join-button"
            id="joinbutton"
          >
              <Link to="/Poker" style={{ textDecoration: "none" }}>
                <h3>Enter</h3>
              </Link>
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
