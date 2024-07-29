import { useState, useEffect } from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../src/Home.css"
import axios from "axios";
import DatePicker from "react-datepicker";
import "../node_modules/react-datepicker/dist/react-datepicker.css"
// import {Link, Switch, Route} from 'react-router-dom';

const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function Home() {
    const serverUrl = `http://${serverIp}:${port}`;
    const [flights, setFilghts] = useState([]);
    const getFlights = ()=>{
        axios.get(serverUrl + "/flightDtls").then((response)=>{
            setFilghts(response.data);
        })
    }

    useEffect(()=>{
        getFlights();
    }, []);


    return ( 
        <>
            <nav className="navbar navbar-expand-lg" style={{"backgroundColor":"white"}}>
                <div className="container-fluid">
                    <a className="navbar-brand logoFont" href="" style={{"color":"black"}}>Fly High</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                        <a className="nav-link active myfont" aria-current="page" href="" style={{"color":"black"}}>Login</a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link myfont" href="" style={{"color":"black"}}>About</a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link myfont" href="" style={{"color":"black"}}>Services</a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link myfont" href="" style={{"color":"black"}}>Contact</a>
                        </li>
                    </ul>
                    </div>
                </div>
            </nav>
            {/* <div className="jumbo">
                <img src="favicon.png" className="logoImg" alt="" />
                <p className="myfont logoText">
                    FlyHigh 
                    Explore Flights 
                    Login
                    Contact Us
                </p>
            </div> */}
            <div style={{"textAlign":"center"}}>
                <div className="searchDiv restDiv">
                    <div style={{"textAlign":"center"}}>
                        <span className="myfont">
                            From:
                        </span>
                        <input type="text" placeholder="Enter a city" className="searchBox myfont" />
                        <span className="myfont">
                            To:
                        </span>
                        <input type="text" placeholder="Enter a city" className="searchBox myfont" />
                        <span className="myfont">
                            Date:
                        </span>
                        <DatePicker className="searchBox myfont" placeholderText="Select Departure Date"></DatePicker>
                        <br />
                        <button className="LinkedInFreeTrail">SEARCH</button>
                    </div>
                </div>
            </div>
            <div className="gridDiv">
                <div className="growCity">
                    <img className="cityImage" src="Bangalore.webp" alt="" />
                </div>
                <div className="growCity" >
                    <img className="cityImage" src="Delhi.jpg" alt="" />
                </div>
                <div className="growCity" >
                    <img className="cityImage" src="Indore.jpg" alt="" />
                </div>
                <div className="growCity" >
                    <img className="cityImage" src="Pune.jpg" alt="" />
                </div>
                <div className="growCity" >
                    <img className="cityImage" src="Jaipur.jpg" alt="" />
                </div>
                <div className="growCity" >
                    <img className="cityImage" src="Mumbai.webp" alt="" />
                </div>
            </div>
        </>
     );
}

export default Home;