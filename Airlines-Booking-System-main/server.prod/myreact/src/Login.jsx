import React from "react";
import { Navigate,Link} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const onEmailChange = (e)=>{
    setEmail(e.target.value);
  }
  
  const onPasswordChange =(e)=>{
    setPassword(e.target.value);
  }

  const serverUrl = `http://${serverIp}:${port}`
  const sendLoginReq = async()=>{
    try{
      const resp = await axios.post(`${serverUrl}/jwt/signin`, {"email":email, "password":password});
      if(resp.status === 200){
        sessionStorage.setItem("jwt",`Bearer ${resp.data.jwt}`);
        sessionStorage.setItem("customerId",resp.data.customerId);
        toast.success("Logged in succesfully");
        navigate("/Home");
      }
    }catch{
      navigate("/Error");
    }

  }

  return (
    <>
      <div style={{"textAlign": "center"}} className="container">
        <div className=" borderStyle" style={{"marginTop":"8%"}} >
          <p className="logoFont" style={{ "fontSize": "50px"}}>Login to FlyHigh</p>
          <input className="searchBox" value={email} onChange={onEmailChange} type="text" name="email" placeholder="Email" />
          <br></br>
          <input className="searchBox" type="password" value={password} onChange={onPasswordChange} name="password" placeholder="Password" />
          <br></br>
          <button className="LinkedInFreeTrail" onClick={sendLoginReq}> Login</button>
          <br></br>
          <p className="myfont greyText"> Haven't registered with us yet?{" "}
            <Link to="/Register">
              <br></br>
              Register now
            </Link></p>
        </div>
      </div>
      <ToastContainer></ToastContainer>
    </>
  );
}

export default Login;
