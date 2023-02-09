import Header from "../header/Header";
import Footer from "../footer/Footer";
import {Link, Route, Routes} from "react-router-dom";
import Join from "../join/Join";
import Login from "../login/Login";
import Container from "../container/Container";
import './App.css'

function App() {
    return (
        <>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Container/>}/>
                    <Route path="/Join" element={<Join/>}/>
                    <Route path="/Login" element={<Login/>}/>
                </Routes>
            </div>
            <Footer/>


        </>
    );
}

export default App;
