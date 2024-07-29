// ONLY FOR ADMIN
import { useState, useEffect } from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../src/Home.css"
import axios from "axios";
// import DatePicker from "react-datepicker";
import "../node_modules/react-datepicker/dist/react-datepicker.css"
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
// import {Link, Switch, Route} from 'react-router-dom';
import { Navigate, useNavigate } from "react-router-dom";
const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function ViewFlights() {
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt"));
    const navigate = useNavigate();

    const viewFlightDetails = ()=>{
        
    }
    const [fromCity, setFromCity] = useState("");
    const [toCity, setToCity] = useState("");
    const [selectedDate, setSelectedDate] = useState("");
    const [searchResult, setSearchResult] = useState(false);
    const serverUrl = `http://${serverIp}:${port}`;
    const [flights, setFilghts] = useState([]);
    const resultNotFoundToast = ()=>{
        toast.error("No results found :(");
    }
    const getFlights = ()=>{
        setSearchResult(true);
        const requestData = {toCity:toCity, fromCity: fromCity, departure:selectedDate+"T00:00:00.00Z"}
        console.log(requestData);
        axios.post(serverUrl + "/flights/all", requestData,
        {
            headers:{Authorization:jwt}
        }).then((response)=>{
            if(response.status == 404){resultNotFoundToast()}
            setFilghts(response.data);
        }).catch((error)=>{
            console.log("executing toast!");
            resultNotFoundToast();
        })
        console.log(flights);
    }

    const handleDateChange = (e)=>{
        const d = e.target.value;
        console.log("THE DATE IS: ", d);
        setSelectedDate(d);
    }

    const onFromCityChange = (e)=>{
        setFromCity(e.target.value);
    }

    const onToCityChange = (e)=>{
        setToCity(e.target.value);
    }

    const deleteFlight = async(id)=>{
        try{
            const resp = await axios.delete(`${serverUrl}/admin/deleteflight/${id}`);
            if(resp.status === 200){
                toast.warn("Flight Deleted");
            }
        }
        catch{
            navigate("/Error");
        }
    }

    // useEffect(()=>{
    //     getFlights();
    // }, []); // no need to fetch at startup, when user clicks on search, then we will fetch all the data
    return ( 
        <>
            <div style={{"textAlign":"center"}}>
                <p className="logoFont centerText">
                    Admin Page
                </p>
                <div className="searchDiv restDiv">
                    <div style={{"textAlign":"center"}}>
                        <div style={{"display":"inline-block"}}>
                        <span className="myfont">
                            From:
                        </span>
                        <input type="text" placeholder="Enter a city" value={fromCity} onChange={onFromCityChange} className="searchBox myfont" />
                        </div>
                        <div style={{"display":"inline-block"}}>
                        <span className="myfont">
                            To:
                        </span>
                        <input type="text" placeholder="Enter a city" value={toCity} onChange={onToCityChange} className="searchBox myfont" />
                        </div>
                       <div style={{"display":"inline-block"}}>
                       <span className="myfont">
                            Date:
                        </span>
                        {/* <DatePicker selected={selectedDate} onChange={handleDateChange} dateFormat="dd/MM/yyyy" className="searchBox myfont" placeholderText="Select Departure Date" ></DatePicker> */}
                        <input type="date" className="searchBox myfont" value={selectedDate} onChange={handleDateChange}></input>
                       </div>
                        <br />
                        <button className="LinkedInFreeTrail" onClick={()=>{getFlights()}}>SEARCH</button>
                    </div>
                </div>
            </div>
            {
                searchResult &&
                <>
                    <div className="container">
                        <div className="row restDiv growDiv">
                        <div className="col"> <p className="myfont textVertical">Flight Name</p></div> {" "}
                            <div className="col"> <p className="myfont textVertical">Flight Class</p></div> {" "}
                            <div className="col"> <p className="myfont textVertical">Destination</p></div> {" "}
                            <div className="col"> <p className="myfont textVertical">Source</p></div> {" "}
                            <div className="col"> <p className="myfont textVertical">Departure</p></div> {" "}
                            <div className="col"> <p className="myfont textVertical">Arrival</p></div> {" "}
                            <div className="col"> <p className="myfont textVertical">Price</p></div> {" "}
                            <div className="col"></div>
                        </div>
                    {flights.map((flight)=>{
                        return (
                            <>      
                                <div className="row restDiv growDiv">
                                    <div className="col"> <p className="myfont textVertical">{flight.name}</p></div> {" "}
                                    <div className="col"> <p className="myfont textVertical">{flight.flightClass}</p></div> {" "}
                                    <div className="col"> <p className="myfont textVertical">{flight.destination}</p></div> {" "}
                                    <div className="col"> <p className="myfont textVertical">{flight.source}</p></div> {" "}
                                    <div className="col"> <p className="myfont textVertical">{flight.departure}</p></div> {" "}
                                    <div className="col"> <p className="myfont textVertical">{flight.arrival}</p></div> {" "}
                                    <div className="col"> <p className="myfont textVertical">Rs.{" "} {flight["farePrice"]}</p></div> {" "}
                                    <div className="col"><button className="tweet" onClick={()=>{
                                        navigate(`/EditFlight/${flight.id}`)
                                    }}>Edit</button></div>
                                </div>
                            </>
                        );
                    })}
                    </div>
                </>
            }
            {
                !searchResult &&
                <>
                    <div className="gridDiv">
                        <div className="growCity">
                            <img className="cityImage" src="Bangalore.webp" alt="" />
                            <img className="cityImage" src="Delhi.jpg" alt="" />
                            <img className="cityImage" src="Indore.jpg" alt="" />
                            <img className="cityImage" src="Pune.jpg" alt="" />
                            <img className="cityImage" src="Jaipur.jpg" alt="" />
                            <img className="cityImage" src="Mumbai.webp" alt="" />
                        </div>
                    </div>
                </>
            }
            <ToastContainer></ToastContainer>
        </>
     );
}

export default ViewFlights;