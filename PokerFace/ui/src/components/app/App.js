import Footer from "../footer/Footer";
import {Route, Routes} from "react-router-dom";
import Join from "../join/Join";
import Login from "../login/Login";
import Poker from "../poker/Poker";
import "./App.css";
import "../../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import RouteManager from "./RouteManager";

function App() {

    return (
        <>
            <div className="App">
                <Routes>
                    <Route path="/Poker" element={<Poker/>}/>
                    <Route path="/Join" element={<Join/>}/>
                    <Route path="/Login" element={<Login/>}/>
                </Routes>
                <RouteManager/>
            </div>
            <Footer/>
        </>
    );
}

export default App;
