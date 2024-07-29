package com.app.services;

import java.util.List;

import com.app.dtos.FlightDTO;
import com.app.dtos.PaymentDTO;
import com.app.dtos.UserDTO;

public interface AdminService {

    List<UserDTO> viewAllUsers();

    String addFlight(FlightDTO flightToAdd);

    String deleteFlight(Integer id);

    FlightDTO getFlightById(Integer id);

    String editFlight(FlightDTO flight,Integer id);

    List<PaymentDTO> allPayments();

    List<PaymentDTO> getPaymentByCid(Integer cid);
    
}
