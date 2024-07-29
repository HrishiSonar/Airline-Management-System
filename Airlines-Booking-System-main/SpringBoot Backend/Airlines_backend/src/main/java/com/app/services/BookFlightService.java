package com.app.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.dtos.AddPassengerDTO;
import com.app.dtos.BookFlightDTO;
import com.app.dtos.PaymentDTO;
import com.app.dtos.ViewProfileDTO;

public interface BookFlightService {

    ResponseEntity<BookFlightDTO> bookFlight(BookFlightDTO bookFlightDto);

    ResponseEntity<?> viewMyBookedFlights(Integer id);

    ResponseEntity<BookFlightDTO> cancelFlight(Integer id);

    ResponseEntity<ViewProfileDTO> viewProfile(Integer id);

    ResponseEntity<AddPassengerDTO> addPassenger(AddPassengerDTO dto, Integer cid);

    ResponseEntity<ViewProfileDTO> editProfile(ViewProfileDTO dto, Integer id);

    ResponseEntity<?> viewPassengers(Integer cid);

    ResponseEntity<?> getAvailableSeats(Integer flightId);

    ResponseEntity<?> makePayment(PaymentDTO paymentDTO);

    
    
}
