package com.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.FlightDtlsDao;
import com.app.daos.PaymentDtlsDao;
import com.app.daos.SeatDetailsDao;
import com.app.daos.UserDao;
import com.app.dtos.FlightDTO;
import com.app.dtos.PaymentDTO;
import com.app.dtos.UserDTO;
import com.app.entities.BookingDetails;
import com.app.entities.FlightDetails;
import com.app.entities.PaymentDetails;
import com.app.entities.SeatDetails;
import com.app.entities.UserDetailsEntity;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserDao uDao;
    @Autowired
    private FlightDtlsDao fDao;
    @Autowired 
    private PaymentDtlsDao pdao;

    @Autowired
    private UserDao udao;

    @Autowired
    private SeatDetailsDao seatDao;

    // @Autowired
    // private ModelMapper modelMapper;

    @Override
    public List<UserDTO> viewAllUsers() {
        List<UserDTO> dtoList=new ArrayList<>();
        List<UserDetailsEntity> list=uDao.findAllUsers();
        for (UserDetailsEntity userDetails : list) {
            UserDTO dto=new UserDTO();
            dto.setCpass(userDetails.getCpass());
            dto.setName(userDetails.getName());
            dto.setEmail(userDetails.getEmail());
            dto.setRole(userDetails.getRole());
            dtoList.add(dto);
        }
        return dtoList;
    }
    @Override
    public String addFlight(FlightDTO flightToAdd) {
        FlightDetails details=new FlightDetails();
        details.setArrival(flightToAdd.getArrival());
        details.setDeparture(flightToAdd.getDeparture());
        details.setDestination(flightToAdd.getDestination());
        details.setFlightClass(flightToAdd.getFlightClass());
        details.setName(flightToAdd.getName());
        details.setSource(flightToAdd.getSource());
        details.setFarePrice(flightToAdd.getFarePrice());
        System.out.println("\n\n" + details + "\n\n");
        FlightDetails temp = fDao.save(details);
        List<SeatDetails> seatList = new ArrayList<>();
        for(int i = 1; i <= 50; i++){
            SeatDetails seat = new SeatDetails();
            seat.setAvailable(true);
            // System.out.println("\n\n" + "TRY" + "\n\n");
            seat.setFlightId(temp);
            // System.out.println("\n\n" + "SUCCESS" + "\n\n");
            seat.setSeatNo("AB" + i);
            seatList.add(seat);
        }
        System.out.println("\n\n" + "Saving seat dtls" + "\n\n");
        seatDao.saveAll(seatList);
        System.out.println("\n\n" + "saved seat dtls" + "\n\n");
        return "added successfully";
    }


    @Override
    public String deleteFlight(Integer id) {
        fDao.deleteById(id);
        return "deleted succesfully";

    }
    @Override
    public FlightDTO getFlightById(Integer id) {
        FlightDetails flight= fDao.findFlightById(id);
        FlightDTO dto=new FlightDTO();
        dto.setArrival(flight.getArrival());
        dto.setDeparture(flight.getDeparture());
        dto.setDestination(flight.getDestination());
        dto.setFlightClass(flight.getFlightClass());
        dto.setName(flight.getName());
        dto.setSource(flight.getSource());
        return dto;
    }
    @Override
    @Transactional
    public String editFlight(FlightDTO flight,Integer id) {
        if(fDao.findFlightById(id) == null) throw new RuntimeException("Resource not found");
        System.out.println("in service "+flight);
        FlightDetails details=new FlightDetails();
        details.setArrival(flight.getArrival());
        details.setDeparture(flight.getDeparture());
        details.setDestination(flight.getDestination());
        details.setFlightClass(flight.getFlightClass());
        details.setName(flight.getName());
        details.setSource(flight.getSource());
        fDao.editFlight(flight.getArrival(),flight.getDeparture(),flight.getDestination(),flight.getFlightClass(),flight.getName(),flight.getSource(),id, flight.getFarePrice());
        return "edited successfully";
    }

    @Override
    public List<PaymentDTO> allPayments() {
        List<PaymentDetails> list=pdao.getAllPayments();
        System.out.println("\n\n" + list + "\n\n");
        List<PaymentDTO> dto=new ArrayList<>();
        for (PaymentDetails p : list) {
            PaymentDTO d=new PaymentDTO();
            // System.out.println("\n\n" + 1 + "\n\n");
            d.setFlightName(p.getBookingId().getFlightId().getName());
            // System.out.println("\n\n" + 2 + "\n\n");
            d.setStatus(p.getStatus());
            // System.out.println("\n\n" + 3 + "\n\n");
            d.setTotalAmount(p.getTotalAmount());
            // System.out.println("\n\n" + 4 + "\n\n");
            d.setUserName(p.getBookingId().getCustomerId().getName());
            dto.add(d);
        }
        return dto;
    }
    @Override
    public List<PaymentDTO> getPaymentByCid(Integer cid) {
        System.out.println("\nExecuting findby cid()\n");
        List<PaymentDetails> paymentEntityList = pdao.findByCustomerId(udao.findCustomerById(cid));
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        for(PaymentDetails ps : paymentEntityList){
            PaymentDTO temp = new PaymentDTO();
            temp.setTotalAmount(ps.getTotalAmount());
            temp.setFlightName(ps.getBookingId().getFlightId().getName());
            temp.setStatus(ps.getStatus());
            temp.setUserName(ps.getCustomerId().getName());
            paymentDTOList.add(temp);
        }
        return paymentDTOList;
    }

    
    
}
