import axios from "axios";
import { useEffect, useState } from "react";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";



// PAYLOAD:
// {
//     "cid": 0,
//     "flightID": 0,
//     "paymentId": 0,
//     "seatNo": [
//       "string"
//     ],
//     "passengerId": [
//       0
//     ],
//     "passengerNames": [
//       "string"
//     ]
// }


// PAYLOAD 1:
// {
//     "flightId": 1,
//     "status": "Successful",
//     "customerId": 1,
//     "totalAmount": 12000
// }

const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function BookFlight(){
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt"));

    const navigate = useNavigate();
    const [flightToBook, setFilghtToBook] = useState({});
    const [upiId, setUpiId] = useState("");
    const [isUpiNull, setIsUpiNull] = useState(true);
    const [selectedSeats, setSelectedSeats] = useState(""); //for single seat
    const [isSeatNull, setIsSeatNull] = useState(true);
    const [isPaymentSuccessful, setIsPaymentSuccessful] = useState(false);
    // const [selectedPassengers, setSelectedPassengers] = useState([]);
    // const [numberOfSeats, setNumberOfSeats] = useState();
    const [paymentDetails, setPaymentDetails] = useState({});
    const [paymentId, setPaymentId] = useState("");
    const [availabeSeats, setAvailableSeats] = useState([]);
    const flightId = useParams();
    const serverUrl = `${serverIp}:${port}`;


    useEffect(()=>{
        getAvailableSeats();
        getFlightToBook();
    }, []);

    const onUpiIdChange = (e)=>{
        setUpiId(e.target.value.trim());
        console.log("'" + upiId + "'");
        if(upiId !== '') {
            setIsUpiNull(false);
        }
        else {
            setIsUpiNull(true);
        }
        console.log("'" + upiId + "'");
    }

    const getAvailableSeats = ()=>{
        console.log("SENDING GET REQ")
        console.log(`http://${serverUrl}/user/getAvailabeSeats/${flightId["flightId"]}`,
        {
            headers:{Authorization:jwt}
        });
        axios.get(`http://${serverUrl}/user/getAvailabeSeats/${flightId["flightId"]}`, {
            headers:{Authorization:jwt}
        }).then((resp)=>{
            setAvailableSeats(resp.data);
        });
    }

    const getFlightToBook = async ()=>{
        const resp = await axios.get(`http://${serverUrl}/flights/findFlightById/${flightId["flightId"]}`,
        {
            headers:{Authorization:jwt}
        });
        setFilghtToBook(resp.data);
        console.log(resp);
        console.log(resp.data.farePrice);
        console.log(flightToBook);
        let temp = {
            "flightId": `${flightId.flightId}`,
            "status": "Successful",
            "customerId": `${customerId}`,
            "totalAmount": `${resp.data.farePrice}`
        }
        setPaymentDetails(temp);
        console.log("temp: " + temp.totalAmount);
        console.log(paymentDetails);
    }

    const makePayment = async ()=>{
        // console.log("\n\n PAYMENT: " + paymentDetails.totalAmount);
        console.log(paymentDetails);
        console.log("\n\n PAYMENT: " + paymentDetails.totalAmount);
        await axios.post(`http://${serverUrl}/user/makePayment`,
        paymentDetails,
        {
            headers:{Authorization:jwt}
        }).then((resp)=>{
            console.log(resp.data);
            console.log(typeof(resp.data));
            console.log("setting paymentid")
            setPaymentId(resp.data);
            if(resp.status == 200){
                toast.success("Payment is successful!");
                setIsPaymentSuccessful(true);
            }

        })
        
        console.log("\n\n PAYMENT: " + paymentDetails);
    }


    // const [selectedSeats, setSelectedSeats] = useState([]); // for array
    // for array of seats:
    // const seatSelectAction =(seatNo)=>{
    //     // let num = numberOfSeats+1;
    //     // setNumberOfSeats(num);
    //     setSelectedSeats(selectedSeats => [...selectedSeats, seatNo]);
    // }
    // for single seat function:

    const seatSelectAction = (seatNo)=>{
        setSelectedSeats(seatNo);
        setIsSeatNull(false);
    }

    console.log(flightId);

    const bookFlight = async ()=>{
        // <Navigate to="/paymentGateway" state={paymentDetails}></Navigate>
        // {
        //     "cid": 0,
        //     "flightID": 0,
        //     "paymentId": 0,
        //     "seatNo": [
        //       "string"
        //     ]
        // }

        await axios.post(`http://${serverUrl}/user/bookFlight`,{
            "cid": customerId,
            "flightID": flightId.flightId,
            "paymentId": paymentId,
            "seatNo": [
              selectedSeats
            ]
        },
        {
            headers:{Authorization:jwt}
        }).then((resp)=>{
            toast.success("Flight Booked Successfully!");
        }).catch((err)=>{
            toast.error("something went wrong );");
        })

    }

    return (
        <>
            <div className="container">
                {
                    availabeSeats.map((seat)=>{
                        return(
                            <>
                                <button className="tweet" onClick={()=>{seatSelectAction(seat["seatNo"])}}>{seat["seatNo"]}</button>
                            </>
                        )
                    })
                }
            </div>
            <div className="container">
                <p style={{"marginTop":"10px"}} className="myfont textVertical">Selected Seat:{" "}{selectedSeats}</p>
              
                {/* for multiple seats: */}
                {/* {
                    selectedSeats.map((seat)=>{
                        return(
                            <>
                                {seat}{" "}
                            </>
                        )
                    })
                }</p> */}
                
                {/* <input type="text" disabled defaultValue={selectedSeats} className="searchBox myfont" /> */}

                <div className="restDiv">
                    <h3 className="logoFont centerText" >Make Payment</h3>

                    <p className="myfont textVertical">Enter your UPI Id:</p>
                    <input type="text" value={upiId} onChange={onUpiIdChange} className="searchBox myfont" />

                    <p className="myfont textVertical">
                        Total Amount to be paid: {flightToBook.farePrice}
                    </p>
                    
                    {!isUpiNull && !isSeatNull && <button className="tweet" onClick={makePayment}>Pay now</button>}
                    <div className="centerText">
                        {isPaymentSuccessful && <button className="LinkedInFreeTrail" onClick={bookFlight}>
                            Book Flight Now!
                        </button>}
                    </div>
                </div>
            </div>
            <ToastContainer></ToastContainer>
        </>
    );
}

export default BookFlight;