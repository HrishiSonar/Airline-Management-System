package com.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.FlightDTO;
import com.app.dtos.UserDTO;
import com.app.services.AdminService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/viewallusers")//done
    public ResponseEntity<?> viewAllUsers() {
        System.out.println(service.viewAllUsers());
        List<UserDTO> list = service.viewAllUsers();
        if(list.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(list);
        
    }
    
    @PostMapping("/createflight")//done
    public ResponseEntity<?> CreateFlight(@RequestBody FlightDTO flightToAdd){
        System.out.println(flightToAdd);
        System.out.println("\n ADDED FLIGHT: " + service.addFlight(flightToAdd));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @DeleteMapping("/deleteflight/{id}")//done
    public ResponseEntity<?> deleteFlight(@PathVariable Integer id){
        System.out.println(id);
        System.out.println("\n\n" + service.deleteFlight(id));
        return ResponseEntity.ok().build();

    }
    @PutMapping("/editFlight/{id}") //DONE
    public ResponseEntity<?> editFlight(@RequestBody FlightDTO flight,@PathVariable Integer id){
        System.out.println(flight);
        System.out.println(id);
        System.out.println("\n\n" + service.editFlight(flight,id));
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getflight/{id}")//done
    public ResponseEntity<?> getFlight(@PathVariable Integer id){
        return ResponseEntity.ok(service.getFlightById(id));
    }

    // public String assignRoles(){

    // }
    @GetMapping("/allpayment") //DONE
    public ResponseEntity<?> viewAllPayments(){
        return ResponseEntity.ok(service.allPayments());
    }

    @GetMapping("/getPaymentByCid/{cid}") //DONE
    public ResponseEntity<?> getMethodName(@PathVariable Integer cid) {
        return ResponseEntity.ok(service.getPaymentByCid(cid));
    }
    
    
    
}
