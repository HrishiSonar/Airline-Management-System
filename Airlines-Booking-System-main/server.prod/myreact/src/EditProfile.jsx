import { useState, useEffect } from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../src/Home.css"
import axios from "axios";
import DatePicker from "react-datepicker";
import "../node_modules/react-datepicker/dist/react-datepicker.css"
import {useParams, useNavigate} from 'react-router-dom';
import {ToastContainer, toast} from 'react-toastify'

// TO send:
/*
{
  "customerId": 0,
  "dob": "2024-02-21",
  "address": "string",
  "mobileNumber": "string",
  "aadhar": "string",
  "gender": "MALE",
  "pincode": {
    "pincode": 0,
    "state": "string",
    "city": "string"
  },
  "name": "string",
  "email": "string",
  "cpass": "string"
}
*/
// view profile:
/*
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
function EditProfile() {
    const navigate = useNavigate();
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt"));
    const [passenger,setPassenger]=useState({});
    const serverUrl=`http://${serverIp}:${port}`;

    const getDetails = async ()=>{
        try{
            const resp = await axios.get(`${serverUrl}/user/viewProfile/${customerId}`, 
            {
                headers:{Authorization:jwt}
            });
            if(resp.status === 200){    
                setPassenger(resp.data);
                setName(resp.data["name"]);
                setMobile(resp.data["mobileNumber"]);
                setDob(resp.data["dob"]);
                setEmail(resp.data["email"]);
                setAdhar(resp.data["aadhar"]);
                setGender(resp.data["gender"]);
                setPassword(resp.data["cpass"]);
                setAddress(resp.data["address"]);
            }
        }catch{
            navigate("/Error");
        }

    }

    useEffect(()=>{
        getDetails();
    }, [])


    const [name, setName] = useState("");
    const [mobile, setMobile] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");  
    const [dob, setDob] = useState("");
    const [gender, setGender] = useState("");   
    const [address, setAddress] = useState("");
    const [adhar, setAdhar] = useState("");   
    
    const onNameChange = (e)=>{
        setName(e.target.value);
    }

    const onMobileChange = (e)=>{
        setMobile(e.target.value);
    }

    const onEmailChange = (e)=>{
        setEmail(e.target.value);
    }

    const onPasswordChange = (e)=>{
        setPassword(e.target.value);
    }

    const onDobChange = (e)=>{
        setDob(e.target.value);
    }

    const onGenderChange = (e)=>{
        setGender(e.target.value);
    }

    const onAddressChange = (e)=>{
        setAddress(e.target.value);
    }

    const onAdharChange = (e)=>{
        setAdhar(e.target.value);
    }

    const sendUpdateReq = async()=>{
        try{
            const resp = await axios.put(`${serverUrl}/user/editProfile/${customerId}`, {
                "dob": dob,
                "address": address,
                "mobileNumber": mobile,
                "aadhar": adhar,
                "gender": gender,
                "pincode": passenger.pincode,
                "name": name,
                "email": email,
                "cpass": password
            },
            {
                headers:{Authorization:jwt}
            });

            if(resp.status === 200){
                toast.success("Profile edited");
            }
        }catch{
            navigate("/Error")
        }
    }


    return (  
        <>
        <div className="container" style={{"textAlign":"left","position":"relative"}}>
            <div className="restDiv growDiv ">
                <div className ="image">
                    <img className="profileImage " src="Delhi.jpg" alt="" />   
                </div> 
                <div  className="editDiv">
                    <input type="text" value={name} onChange={onNameChange} className="profileBox myfont  greyText" />
                    <input type="text" value={mobile} onChange={onMobileChange} className="profileBox myfont greyText" />
                    <input type="text" value={email} onChange={onEmailChange} className="profileBox myfont greyText" />
                </div>
            </div> 
        </div>
        <div className="container"  style={{"textAlign":"center" ,"marginTop":"10px"}}>
    
          
            <div className="restDiv" style={{"textAlign":"left"}}>
            <div style={{"marginBottom":"5px"}} >
                <p  className="myfont">  Password : </p>
                <input type="password" value={password} onChange={onPasswordChange}  className="profileBox myfont greyText" />
            </div>
            <div style={{"marginBottom":"5px"}}>
                <p className="myfont"> DateOfBirth : </p>
                <input type="text" value={dob} onChange={onDobChange}  className="profileBox myfont  greyText" />
              
            </div>
            <div style={{"marginBottom":"5px"}}>
                <p className="myfont">Gender :</p>
                <input type="text" value={gender} onChange={onGenderChange}  className="profileBox myfont greyText" />
            </div>
            <div style={{"marginBottom":"5px"}}>
                <p className="myfont">Address :</p>
                <input type="text" value={address} onChange={onAddressChange}  className="profileBox myfont greyText" />
            </div>
            <div style={{"marginBottom":"5px"}}>
                <p className="myfont">AadharNo :</p>
                <input type="number" value={adhar} onChange={onAdharChange}  className="profileBox myfont greyText" />
            </div>

            <div style={{"marginBottom":"5px"}}>
                <button className="tweet" style={{"marginTop":"10px" }} onClick={sendUpdateReq}>Update Profile </button>
            </div>
            </div>

        </div>
        <ToastContainer></ToastContainer>

        </>
    );
}

export default EditProfile;