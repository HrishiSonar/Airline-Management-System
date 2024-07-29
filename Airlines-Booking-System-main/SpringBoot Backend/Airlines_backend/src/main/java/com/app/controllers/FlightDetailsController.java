package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.AddUserDTO;
import com.app.dtos.FlightDetailsDTO;
import com.app.entities.FlightDetails;
import com.app.services.FlightDtlsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FlightDetailsController{
	
	@Autowired
	private FlightDtlsService service;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllFlights() {
		return ResponseEntity.ok(service.getAllFlights());
	}

	@PostMapping("/all")
	public ResponseEntity<List<FlightDetails>> postMethodName(@RequestBody FlightDetailsDTO details) {
		System.out.println(details);
		return service.getCustomeFlightDetails(details.getToCity(), details.getFromCity(), details.getDeparture());
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addUser(@RequestBody AddUserDTO user) {
		return service.addUser(user);
	}

	@GetMapping("/getPincodes")
	public ResponseEntity<?> getPincodes() {
		return service.getPincodes();
	}
	
	@GetMapping("/findFlightById/{id}")
	public ResponseEntity<?> findFlightById(@PathVariable Integer id) {
		System.out.println("\n\n" + "RECIEVED" + "\n\n");
		return service.getFlightById(id);
		
	}

}
