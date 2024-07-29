import React from "react";
//import { Switch, Link, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function About()
{
return(
    <>
    <div style={{"padding":"10px"}}>
    <p className="logoFont titleStyle"  >  Cutting-Edge Technology:</p> 
    <p className="myfont aboutPara" > Our state-of-the-art airline reservation system leverages the latest technology to bring you real-time information, ensuring that you have access to the most up-to-date flight schedules, prices, and availability.</p>
    <hr />
    <br />
    <p className="logoFont titleStyle">User-Friendly Interface</p> 
    <p className="myfont aboutPara">We prioritize simplicity and ease of use. Our intuitive interface allows you to effortlessly navigate through a wide array of flight options, compare prices, and make reservations with just a few clicks.</p>
    <hr />
    <br />
    
    <p className="logoFont titleStyle">Comprehensive Flight Options</p>
    <p className="myfont aboutPara" > Whether you're a business traveler, a family on vacation, or a solo adventurer, FlyHigh offers a diverse range of flight options to suit every budget and preference. Explore a vast network of airlines and routes to find the perfect itinerary for your journey.
    </p>
    <hr />

    <br />
    <p className="logoFont titleStyle">Transparent Pricing</p> 
    <p className="myfont aboutPara">At FlyHigh, we believe in transparency. Our pricing structure is straightforward, with no hidden fees. Enjoy the peace of mind that comes with knowing the true cost of your journey right from the start.</p>
    <hr />
    <br />

    <p className="logoFont titleStyle">24/7 Customer Support</p> 
    <p className="myfont aboutPara">Your satisfaction is our top priority. Our dedicated customer support team is available around the clock to assist you with any inquiries, changes, or unexpected situations that may arise during your travel experience.</p>
    <hr />
    <br />

    <p className="logoFont titleStyle">Security and Privacy</p> 
    <p className="myfont aboutPara">Your data and personal information are of the utmost importance to us. We employ robust security measures to ensure that your transactions and personal details are safeguarded throughout the reservation process.
    Join Us on Your Next Adventure</p>
    <hr />
    <br />

    <p className="myfont aboutPara">Embark on a journey with FlyHigh and redefine your travel experience. Whether you're jetting off for business or pleasure, our airline reservation system is here to make your travel dreams a reality. Discover the world with confidence, knowing that you have a trusted partner in FlyHigh.</p>
    

    <p style={{"textAlign":"center"}}   className="myfont titleStyle"> Welcome aboard, and happy travels!</p>
   <hr></hr>
    </div>

    <div>
        <div style={{"textAlign":"center"}}>
                <div className="searchDiv restDivColor">
                    <div style={{"textAlign":"center"}}>
                        <div style={{"display":"inline-block"}}>
                        <span className="myfont">
                    <h1 className="orangeFont" >CONTACT US </h1>
                     <br></br>
                    (Monday to Friday , 9am to 7pm PST)
                    <br></br>
                    <br></br>
                    India Toll-Free :
                    <br></br>
                     91+787383738
                     <br></br>
                     <br></br>
                    International :
                    <br></br>
                    1-087363-373

        </span>
        </div>
         </div>
        </div>
    </div>
    </div>
    
    
    </>
)
}
export default About;