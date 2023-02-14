import Footer from "../footer/Footer";
import {Link, Route, Routes} from "react-router-dom";
import Join from "../join/Join";
import Login from "../login/Login";
import Poker from "../poker/Poker";
import './App.css';
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Nav from "../header/Nav";
import {useEffect, useState} from "react";

function App() {

    return (
        <>

            <div className="App">
                <Routes>
                    <Route path="/Poker" element={<Poker/>}/>
                    <Route path="/" element={<Join/>}/>
                    <Route path="/Login" element={<Login/>}/>
                </Routes>
            </div>
            <Footer/>
        </>
    );
}

export default App;
