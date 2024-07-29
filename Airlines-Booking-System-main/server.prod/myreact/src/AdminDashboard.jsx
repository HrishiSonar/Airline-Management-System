import { useNavigate } from "react-router-dom";

function AdminDashboard(){
    const navigate = useNavigate();
    return(
        <>
            <div className="container">
                <p className="logoFont centerText">
                    Admin Dashboard
                </p>
                <div className="centerText">

                    <div className="gridDiv" style={{"display":"inline-block"}} onClick={()=>{
                        navigate("/ViewUsers")
                    }}>
                        <p className="logoFont">View Users</p>
                    <img src="viewUsers.jpg" className="cityImage" alt=""/></div>
                    <div className="gridDiv" style={{"display":"inline-block"}} onClick={()=>{
                        navigate("/CreateFlight")
                    }}>
                        <p className="logoFont">Create Flight</p>
                        <img src="createFlight.jpg" className="cityImage" alt="" />
                    </div>
                    <div className="gridDiv" style={{"display":"inline-block"}} onClick={()=>{
                        navigate("/ViewFlights")
                    }}>
                        <p className="logoFont">View Flights</p>
                        <img src="viewFlights.jpg" className="cityImage" alt="" />
                    </div>
                    <div className="gridDiv" style={{"display":"inline-block"}} onClick={()=>{
                        navigate("/ViewPayments")
                    }}>
                        <p className="logoFont">View Payments</p>
                        <img src="viewPayments.jpg" className="cityImage" alt="" />
                    </div>
                </div>
            </div>
        </>
    )
}

export default AdminDashboard;