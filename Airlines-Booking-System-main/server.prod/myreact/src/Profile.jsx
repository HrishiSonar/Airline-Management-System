import { useState, useEffect } from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../src/Home.css"
import axios from "axios";
import DatePicker from "react-datepicker";
import "../node_modules/react-datepicker/dist/react-datepicker.css"
import { Navigate, useNavigate, useParams } from "react-router-dom";



/*
{
  "customerId": 1,
  "dob": "2000-01-01",
  "address": "phase 3",
  "mobileNumber": "1233",
  "aadhar": "93759843",
  "gender": "MALE",
  "pincode": {
    "pincode": 456006,
    "state": "Madhya Pradesh",
    "city": "Ujjain"
  },
  "name": "Sagar Khatri",
  "email": "sagar@gmail.com",
  "cpass": "sagar123"
}
*/
const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function Profile() {
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt")); // sessionStorage.getItem("jwt");
    const navigate = useNavigate();
        const [passenger,setPassenger]=useState({});
        const serverUrl=`http://${serverIp}:${port}`;

        const getDetails = async ()=>{
            try{
                setCustomerId(sessionStorage.getItem("customerId"));
                setJwt(sessionStorage.getItem("jwt"));
                console.log("CUST: " + customerId);
                console.log("jwt: " + jwt);
                console.log("SESS CUS: " + sessionStorage.getItem("customerId"));
                const resp = await axios.get(`${serverUrl}/user/viewProfile/${customerId}`,
                {
                    headers:{Authorization:jwt}
                });
                if(resp.status === 200){
                    setPassenger(resp.data);
                }
            }catch{
                navigate("/Error");
            }
        }

        useEffect(()=>{
            getDetails();
        },[]);


    return (  
        <>

            <div className="container" style={{"textAlign":"left","position":"relative"}}>
                <div className="restDiv growDiv ">
                    <div style={{"display":"inline-block"}}>
                        <img  className="profileImage " src="/Delhi.jpg" alt="" />   
                    </div> 
                    <div  style={{"display":"inline-block" ,"width":"60%","marginLeft":"200px", "textAlign":""}}>
                        <p className="myfont">Name : <span className="greyText">{passenger["name"]}</span></p>
                        <p className="myfont">Mobile : <span className="greyText">{passenger["mobileNumber"]}</span></p>
                        <p className="myfont">Email : <span className="greyText">{passenger["email"]}</span></p>
                    </div>
                </div> 
            </div>

            <div className="container"  style={{"textAlign":"center" ,"marginTop":"10px"}}>
                <div className="restDiv" style={{"textAlign":"left"}}>
                    <hr />
                    <div>
                        <p className="myfont"> DateOfBirth : </p>
                        <p className=" myfont greyText" >{passenger["dob"]}</p>
                    </div>
                    <hr />
                    <div>
                        <p className="myfont">Gender :</p>
                        <p className=" myfont greyText" >{passenger["gender"]}</p>
                    </div>
                    <hr />
                    <div>
                        <p className="myfont">Address :</p>
                        <p className=" myfont greyText" >{passenger["address"]}</p>
                    </div>
                    <hr />
                    <div>
                        <p className="myfont">AadharNo :</p>
                        <p className=" myfont greyText" >{passenger["aadhar"]}</p>
                    </div>
                    <hr />
                    <div>
                        <button className="tweet" style={{"marginTop":"10px" }} onClick={()=>{navigate("/editprofile")}}>Edit Profile </button>
                    </div>
                </div>

            </div>
        </>
    );
}

export default Profile;