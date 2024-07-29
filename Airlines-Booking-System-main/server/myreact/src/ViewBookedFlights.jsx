// [
//     {
//       "flightName": "Akasa",
//       "customerName": "Sagar Khatri",
//       "source": "Mumbai",
//       "destination": "Bangalore",
//       "totalAmount": 7503,
//       "seatNo": "AB1",
//       "departure": "2024-02-19T12:11:15.926",
//       "arrival": "2024-02-19T14:11:15.926",
//       "duration": "02:00:00"
//     }
// ]

import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function ViewBookedFlights(){
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt"));
    const navigate = useNavigate();
    const serverUrl = `http://${serverIp}:${port}`
    const [bookingData, setBookingData] = useState([]);
    console.log(customerId);

    const getBookingDetails = async ()=>{
        try{
            const resp = await axios.get(`${serverUrl}/user/viewBookedFlights/${customerId}`,
            {
                headers:{Authorization:jwt}
            });
            setBookingData(resp.data);
        }
        catch{
            navigate("/Error");
        }
    }

    useEffect(()=>{
        getBookingDetails();
        console.log(bookingData);
    }, []);


    return(
        <>
            <div className="container">
                <p className="logoFont centerText">Booked Tickets</p>
            {
                bookingData.map((ticket)=>{
                    return(
                        <div className="restDiv row">
                            <div className="col">
                                <p className="myfont">
                                    Flight Name: {ticket["flightName"]}
                                </p>
                                <p className="myfont">
                                    Flight From: {ticket["source"]}
                                </p>
                                <p className="myfont">
                                    Flight To: {ticket["destination"]}
                                </p>
                                
                            </div>
                            <div className="col">
                                <p className="myfont">
                                    Passanger Name: {ticket["customerName"]} 
                                </p>
                                <p className="myfont">
                                    Seat Number: {ticket["seatNo"]}
                                </p>
                                <p className="myfont">
                                    Total Amount: {ticket["totalAmount"]}
                                </p>
                            </div>
                            <div className="col">
                                <p className="myfont">
                                    Flight Departure Time: {ticket["departure"]}
                                </p>
                                <p className="myfont">
                                    Flight Arrival Time: {ticket["arrival"]}
                                </p>
                                <p className="myfont">
                                    Flight Duration: {ticket["duration"]}
                                </p>
                            </div>
                            
                        </div>
                    )
                })
            }
            </div>
        </>
    )
}

export default ViewBookedFlights;