// Response data :

import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

/*
[
  {
    "flightName": "Akasa",
    "flightId": null,
    "userName": "Sagar Khatri",
    "status": "Successful",
    "customerId": null,
    "totalAmount": 5567
  }
]
*/
const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function ViewPaymetns(){
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt"));
    const serverUrl = `http://${serverIp}:${port}`
    const [paymentData, setPaymentData] = useState([]);
    const navigate = useNavigate();
    const getPaymentData = async()=>{
        try{
            const resp = await axios.get(`${serverUrl}/admin/allpayment`,
            {
                headers:{Authorization:jwt}
            });
            if(resp.status === 200){
                setPaymentData(resp.data);
            }
        }
        catch{
            navigate("/Error");
        }
        
    }

    useEffect(()=>{
        getPaymentData();
    }, [])

    return (
        <>
            <div className="container">
                {
                    paymentData.map((payment)=>{
                        return(
                            <div className="restDiv">
                                <p className="myfont">Flight Name: {payment["flightName"]}</p>
                                <p className="myfont">Customer Name: {payment["userName"]}</p>
                                <p className="myfont">Total Amount: {payment["totalAmount"]}</p>
                                <p className="myfont">Payment Status: {payment["status"]}</p>
                            </div>
                        )
                    })
                }
            </div>
        </>
    )
}

export default ViewPaymetns;