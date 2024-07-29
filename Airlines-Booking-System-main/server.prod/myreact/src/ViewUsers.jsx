// response: 
/*
[
  {
    "name": "Sagar Khatri",
    "email": "sagar@gmail.com",
    "cpass": "sagar123",
    "role": "USER"
  }
]
*/

import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const port = process.env.REACT_APP_PORT_NO;
const serverIp = process.env.REACT_APP_SERVER_IP;
function ViewUsers(){
    const [customerId, setCustomerId] = useState(sessionStorage.getItem("customerId"));// sessionStorage.getItem("customerId");
    const [jwt, setJwt] = useState(sessionStorage.getItem("jwt"));
    const serverUrl = `http://${serverIp}:${port}`
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();
    const getUsers = async ()=>{
        const resp = await axios.get(`${serverUrl}/admin/viewallusers`,
        {
            headers:{Authorization:jwt}
        });
        if(resp.status === 200){
            setUsers(resp.data);
        }
        else{
            navigate("/Error");
        }
    }

    useEffect(()=>{
        getUsers();
    },[])

    
    return (
        <>
            <div className="container">
                {
                    users.map((user)=>{
                        return(
                            <div key={user["email"]} className="restDiv">
                                <p className="myfont">Name: {user["name"]}</p>
                                <p className="myfont">Email: {user["email"]}</p>
                                <p className="myfont">Password: {user["cpass"]}</p>
                                <p className="myfont">Role: {user["role"]}</p>
                            </div>
                        )
                    })
                }
            </div>
        </>
    )
}

export default ViewUsers;