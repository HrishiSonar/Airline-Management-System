package com.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.AddPassengerDTO;
import com.app.dtos.BookFlightDTO;
import com.app.dtos.PaymentDTO;
import com.app.dtos.ViewProfileDTO;
import com.app.services.BookFlightService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/user")
// @CrossOrigin(allowedHeaders = "*",origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserController {
    @Autowired
    private BookFlightService service;
    
    @Autowired
    private HttpSession session;

    @CrossOrigin
    @GetMapping("/see")
    public void testing() {
		System.out.println("email mila  "+ (Integer)session.getAttribute("userid"));

    }

    @CrossOrigin
    @GetMapping("/getAvailabeSeats/{flightId}")
    public ResponseEntity<?> getAvailableSeats(@PathVariable  Integer flightId) {
    	System.out.println("hieee im hereeee in getavailable seats wale api mai "+flightId);

        return service.getAvailableSeats(flightId);
    }
    

    @CrossOrigin
    @PostMapping("/bookFlight") // TESTED!!!!!!
    public ResponseEntity<?> postMethodName(@RequestBody BookFlightDTO bookFlightDto) {
        System.out.println("here  "+bookFlightDto);

        return service.bookFlight(bookFlightDto);
    }


    @CrossOrigin
    @GetMapping("/viewBookedFlights/{id}") // DONE ++
    public ResponseEntity<?> viewBookedFlights(@PathVariable Integer id) {
        return service.viewMyBookedFlights(id);
    }
    

    @CrossOrigin
    @DeleteMapping("/cancelFlight/{id}") // DONE ++
    public ResponseEntity<?> cancelFlight(@PathVariable Integer id) {
        return service.cancelFlight(id);
    }
    

    @CrossOrigin
    @PutMapping("/editProfile/{id}") // DONE 
    public ResponseEntity<?> editProfile(@RequestBody ViewProfileDTO dto, @PathVariable Integer id) {
        return service.editProfile(dto, id);
    }


    @CrossOrigin
    @GetMapping("/viewProfile/{id}") // DONE
    public ResponseEntity<?> viewProfileById(@PathVariable Integer id) {
        return service.viewProfile(id); 
    }
    

    @CrossOrigin
    @PostMapping("/addPassenger/{cid}") // DONE!
    public ResponseEntity<?> addPassenger(@RequestBody AddPassengerDTO dto, @PathVariable Integer cid) {
        return service.addPassenger(dto, cid);
    }


    @CrossOrigin
    @GetMapping("/viewPassengers/{cid}") // DONE!
    public ResponseEntity<?> viewPassengers(@PathVariable Integer cid) {
        return service.viewPassengers(cid);
    }


    @CrossOrigin
    @PostMapping("/makePayment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("\n\n" + "inside user Controller!" + "\n\n");
        return service.makePayment(paymentDTO);
    }
    
    
    // @RequestMapping(value = "/viewProfile", method = RequestMethod.OPTIONS)
    // public ResponseEntity<?> optionsEndpoint() {
    //     System.out.println("\n\n" + "REQ RECIEVED" + "\n\n");
    //     // Respond to OPTIONS request with appropriate CORS headers
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("Access-Control-Allow-Origin", "*"); // or specific origins
    //     headers.add("Access-Control-Allow-Methods", "PUT, DELETE, GET, POST, OPTIONS");
    //     headers.add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
    //     headers.add("Access-Control-Max-Age", "3600");
    //     return new ResponseEntity<>(headers, HttpStatus.OK);
    // }
}
