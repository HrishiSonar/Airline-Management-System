import { useState, useEffect } from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../src/Home.css"
import axios from "axios";
import "../node_modules/react-datepicker/dist/react-datepicker.css"
import { Dropdown, DropdownButton } from "react-bootstrap";
import { Navigate, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
// Register user JSON:

// {
//     "name": "test1",
//       "cpass": "test@123",
//       "email": "test@gmail.com",
//       "role": "USER",
//       "aadhar": "123CC",
//       "dob": "2020-02-18",
//       "gender": "MALE",
//       "mobileNumber": "1234",
//       "pincode": {
//         "pincode": 482001,
//         "state": "MP",
//         "city": "Jabalpur"
//       },
//       "address": "MP"
// }
const serverIp = "http://" + process.env.REACT_APP_SERVER_IP +":"+ process.env.REACT_APP_PORT_NO;

function UserProfile(props) {
    
    const getPincodes = async ()=>{
        await axios.get(`${serverIp}/flights/getPincodes`).then((resp)=>{
            setPincodeSelector(resp.data);
        });
    }

    console.log(serverIp);
    useEffect(()=>{getPincodes()},[]);
    const navigate = useNavigate();
    const [fullName, setFullName] = useState("");
    const [email, setEmail] = useState("");
    const [mobile, setMobile] = useState("");
    const [dob, setDob] = useState("");
    const [gender, setGender] = useState("");
    const [aadhar, setAadhar] = useState("");
    const [password, setPassword] = useState("");
    const [address, setAddress] = useState("");
    const [pincode, setPincode] = useState("");
    const [state, setState] = useState("");
    const [city, setCity] = useState("");
    const [pincodeSelector, setPincodeSelector] = useState([]);


    useEffect(()=>{
        getPincodes()
    }, []);

    console.log(pincodeSelector);
    console.log(typeof(pincodeSelector));

    const onFullNameChange=(e)=>{
        setFullName(e.target.value);
    }

    const onEmailChange=(e)=>{
        setEmail(e.target.value);
    }

    const onMobileChange=(e)=>{
        setMobile(e.target.value);
    }

    const onDobChange=(e)=>{
        const date = (e.target.value);
        setDob(date);
    }

    const onGenderChange=(e)=>{
        setGender(e.target.value);
    }

    const onAadharChange=(e)=>{
        setAadhar(e.target.value);
    }

    const onPasswordChange=(e)=>{
        setPassword(e.target.value);
    }

    const onAddressChange=(e)=>{
        setAddress(e.target.value);
    }

    const RegisterUser = ()=>{
        const payload = {
            "firstName": fullName,
              "password": password,
              "email": email,
              "role": "ROLE_USER",
              "aadhar": aadhar,
              "dob": dob,
              "gender": gender,
              "mobileNumber": mobile,
              "pincode": {
                "pincode": pincode,
                "state": state,
                "city": city
              },
              "address": address
            }

        axios.post(`${serverIp}/jwt/signup`, payload).then((resp)=>{
            if(resp.status == 200) toast.success("Successfully registered, now you can login")
            navigate("/login");
        })
    }

    return (  
        <>
        
        <div className="container"  style={{"textAlign":"center" ,"marginTop":"10px"}}>
            
          {/* <div className="restDiv" style={{"textAlign":"left", "backgroundRepeat":"no-repeat","backgroundSize":"fill","backgroundImage":"url(planepic.jpeg)"}} > */}
          <div className="restDiv" style={{"textAlign":"left"}}>
            <h1 className="logoFont centerText">Register Now</h1>
                <div>
                    <p  className="myfont"> Full Name : </p>
                    <input type="text" value={fullName} onChange={onFullNameChange} className="searchBox myfont greyText" />
                </div>

                <div>
                    <p className="myfont"> Email : </p>
                    <input type="text"  value={email} onChange={onEmailChange} className="searchBox myfont  greyText" />
                
                </div>
                <div>
                    <p className="myfont"> Mobile:</p>
                    <input type="number"  value={mobile} onChange={onMobileChange} className="searchBox myfont greyText" />
                </div>
                <div>
                    <p className="myfont">Date of birth :</p>
                    <input type="date"  value={dob} onChange={onDobChange} className="searchBox myfont greyText" />
                </div>
                <div>
                <DropdownButton id="dropdown-item-button" title="Gender">
                    <Dropdown.ItemText>Select your Gender</Dropdown.ItemText>
                    <Dropdown.Item as="button" onClick={()=>{setGender("MALE"); console.log(gender);}}>MALE</Dropdown.Item>
                    <Dropdown.Item as="button" onClick={()=>{setGender("FEMALE"); console.log(gender);} }>FEMALE</Dropdown.Item>
                </DropdownButton>
                <input type="text" defaultValue = {gender} readOnly className="searchBox myfont greyText" />
                </div>

                <div>
                    <p className="myfont">Adhar Card Number :</p>
                    <input type="number" value={aadhar} onChange={onAadharChange} className="searchBox myfont greyText" />
                </div>

                <div>
                    <p className="myfont">Password :</p>
                    <input type="password"  value={password} onChange={onPasswordChange} className="searchBox myfont greyText" />
                </div>

                <div>
                    <p className="myfont"> Address : </p>
                    <input type="text"  value={address} onChange={onAddressChange} className="searchBox myfont  greyText" />
                </div>

                <div>
                    <DropdownButton id="dropdown-item-button" title="City">
                        <Dropdown.ItemText>Select a City</Dropdown.ItemText>
                        {
                            pincodeSelector.map((pins)=>{
                                return (
                                    <>
                                        <Dropdown.Item as="button" onClick={()=>{
                                            setPincode(pins["pincode"]);
                                            setCity(pins["city"]);
                                            setState(pins["state"]);
                                            console.log(pincode + city + state);
                                            }
                                        }>{pins["city"]}</Dropdown.Item>
                                    </>
                                )
                            })
                        }
                        
                    </DropdownButton>
                    <input type="text" disabled defaultValue={pincode} className="mb-5 searchBox myfont greyText" />
                    <input type="text" disabled defaultValue={state}  className="mb-5 searchBox myfont greyText" />
                    <input type="text" disabled defaultValue={city} className="mb-5 searchBox myfont greyText" />
                    {/* <input type="text" value = {gender} className="searchBox myfont greyText" /> */}
                </div>

                <div>
                    <button className="tweet" style={{"marginTop":"10px" }} onClick={RegisterUser}>Create Profile </button>
                </div>
            </div>
        </div>
        <ToastContainer></ToastContainer>
        </>
    );
}

export default UserProfile;