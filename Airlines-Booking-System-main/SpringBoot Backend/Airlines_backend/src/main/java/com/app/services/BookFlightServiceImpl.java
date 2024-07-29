package com.app.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.daos.BookingDtlsDao;
import com.app.daos.FlightDtlsDao;
import com.app.daos.GeneralDtlsDao;
import com.app.daos.PassengerDao;
import com.app.daos.PaymentDtlsDao;
import com.app.daos.SeatDetailsDao;
import com.app.daos.UserDao;
import com.app.dtos.AddPassengerDTO;
import com.app.dtos.BookFlightDTO;
import com.app.dtos.PaymentDTO;
import com.app.dtos.SeatDTO;
import com.app.dtos.ViewBookedFlightDTO;
import com.app.dtos.ViewProfileDTO;
import com.app.entities.BookingDetails;
import com.app.entities.FlightDetails;
import com.app.entities.GeneralDetails;
import com.app.entities.PassengerDetails;
import com.app.entities.PaymentDetails;
import com.app.entities.SeatDetails;
import com.app.entities.StatusEnum;
import com.app.entities.UserDetailsEntity;

@Service
@Transactional
public class BookFlightServiceImpl implements BookFlightService {

    @Autowired
    private SeatDetailsDao seatDao;

    @Autowired
    private BookingDtlsDao bdao;

    

    @Autowired
    private BookingDtlsDao dao;
    @Autowired
    private PassengerDao pdao;
    @Autowired
    private GeneralDtlsDao gdao;
    @Autowired
    private UserDao udao;
    @Autowired
    private FlightDtlsDao fDao;
    @Autowired
    private PaymentDtlsDao paydao;

    @Autowired
    private ModelMapper modelMapper;

  

    @Override
    public ResponseEntity<BookFlightDTO> bookFlight(BookFlightDTO bookFlightDto) { 
        System.out.println("\n\n----------------\n" + bookFlightDto);
        System.out.println("1");
        BookingDetails bookingDetails = new BookingDetails();
        FlightDetails currentFlight = fDao.findById(bookFlightDto.getFlightID()).orElseThrow();
        System.out.println("2");
        bookingDetails.setFarePrice(currentFlight.getFarePrice());
        System.out.println("\n\n" + currentFlight + "\n\n");
        System.out.println("3");

        bookingDetails.setDuration(LocalTime.ofSecondOfDay(currentFlight.getArrival().toLocalTime().toSecondOfDay()-currentFlight.getDeparture().toLocalTime().toSecondOfDay()));

        if (!bookFlightDto.getPassengerid().isEmpty()){
            bookingDetails.setPassengerId(pdao.findAllById(bookFlightDto.getPassengerid()));
        }
        UserDetailsEntity customer=udao.findCustomerById(bookFlightDto.getCid());
        bookingDetails.setCustomerId(customer);
        // System.out.println(customer.getName());
        System.out.println("8");
        PaymentDetails payment=paydao.findPaymentById(bookFlightDto.getPaymentId());
        System.out.println("9");
        bookingDetails.setPaymentID(payment);
        // System.out.println(payment.getStatus());
        System.out.println("10");

        FlightDetails flight=fDao.findFlightById(bookFlightDto.getFlightID());
        System.out.println("11");
        System.out.println("\n\n DTO: " + bookFlightDto +"\n\n");
        bookingDetails.setFlightId(flight);
        //  System.out.println(flight.getName());
        dao.save(bookingDetails);
        System.out.println("\n\n" + bookingDetails.getId() + "\n\n");
        for(String seat : bookFlightDto.getSeatNo()){
            seatDao.updateSeatDetails(flight.getId(), seat, bookingDetails.getId());
        }
        // System.out.println("\n\n LIST SEAT: " + seatsBooked+"\n\n");
        

        

        return new ResponseEntity<>(bookFlightDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> viewMyBookedFlights(Integer id) {
        List<BookingDetails> dtemp= dao.findByCustomerId(udao.findCustomerById(id));

        List<ViewBookedFlightDTO> dtoList = new ArrayList<>();
        for(BookingDetails d : dtemp){
            List<SeatDetails> seat = seatDao.findByBookingId(d);
            FlightDetails flight = d.getFlightId();
            ViewBookedFlightDTO dto=new ViewBookedFlightDTO();
            dto.setTotalAmount(d.getFarePrice());
            dto.setSource(flight.getSource());
            dto.setDestination(flight.getDestination());
            dto.setFlightName(flight.getName());
            dto.setCustomerName(d.getCustomerId().getName());
            dto.setDuration(LocalTime.ofSecondOfDay((d.getFlightId().getArrival().toLocalTime().toSecondOfDay()-d.getFlightId().getDeparture().toLocalTime().toSecondOfDay())));
            dto.setArrival(flight.getArrival());
            dto.setDeparture(flight.getDeparture());
            dto.setSeatNo(seat.get(0).getSeatNo());
            System.out.println("\n\n" + "1" + "\n\n");
            dtoList.add(dto);
            System.out.println("\n\n" + "FINISH" + "\n\n");
            System.out.println("\n\n" + dtoList + "\n\n");
        }
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<BookFlightDTO> cancelFlight(Integer id) {
        BookingDetails temp = dao.findById(id).orElseThrow();
        // BookFlightDTO flightToBeDeleted = new BookFlightDTO();
        // flightToBeDeleted.setCid(temp.getCustomerId().getId());
        // flightToBeDeleted.setDuration(temp.getDuration());
        // flightToBeDeleted.setFarePrice(temp.getFarePrice());
        // flightToBeDeleted.setFlightID(temp.getFlightId().getId());
        // flightToBeDeleted.setId(temp.getId());
        // flightToBeDeleted.setPaymentId(temp.getPaymentID().getId());
        // flightToBeDeleted.setSeatno(temp.getSeatno());
        bdao.delete(temp);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<ViewProfileDTO> viewProfile(Integer id) {
        UserDetailsEntity details= udao.findById(id).orElseThrow();
        GeneralDetails generalDetails=gdao.findByCustomerId(id);
        ViewProfileDTO dto=modelMapper.map(details, ViewProfileDTO.class);
        dto.setAadhar(generalDetails.getAadhar());
        dto.setAddress(generalDetails.getAddress());
        dto.setDob(generalDetails.getDob());
        dto.setMobileNumber(generalDetails.getMobileNumber());
        dto.setPincode(generalDetails.getPincode());
        dto.setCustomerId(details.getId());
        dto.setGender(generalDetails.getGender());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<AddPassengerDTO> addPassenger(AddPassengerDTO dto, Integer cid) {
        PassengerDetails passenger= modelMapper.map(dto, PassengerDetails.class);
        System.out.println("\n\n Mapped add passenger dto to passenger details entity: \n\n" + passenger);        
        pdao.save(passenger);
        System.out.println("\n\n Passanger added, mapping the passenger to a customer: \n\n");
        UserDetailsEntity customer = udao.findById(cid).orElseThrow();
        List<PassengerDetails> passList = customer.getPassengerId();
        passList.add(passenger);
        customer.setPassengerId(passList);
        System.out.println("\n\n mapped\n\n");
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ViewProfileDTO> editProfile(ViewProfileDTO dto, Integer id) {
        UserDetailsEntity userToUpdate= udao.findCustomerById(id);
        GeneralDetails generalDetails= gdao.findByCustomerId(id);
        userToUpdate.setCpass(dto.getCpass());
        userToUpdate.setEmail(dto.getEmail());
        userToUpdate.setName(dto.getName());

        udao.editProfile(userToUpdate.getCpass(),userToUpdate.getEmail(),userToUpdate.getName(), userToUpdate.getId());


        generalDetails.setAadhar(dto.getAadhar());
        generalDetails.setAddress(dto.getAddress());
        generalDetails.setDob(dto.getDob());
        generalDetails.setGender(dto.getGender());
        generalDetails.setMobileNumber(dto.getMobileNumber());
        generalDetails.setPincode(dto.getPincode());

        gdao.editProfile(generalDetails.getAadhar(),generalDetails.getAddress(),generalDetails.getDob(),generalDetails.getGender().name(), generalDetails.getMobileNumber(),generalDetails.getPincode().getPincode());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<?> viewPassengers(Integer cid) {
        List<Integer> idList = udao.findAllPassengersByCid(cid);
        List<PassengerDetails> passList = new ArrayList<>();
        for(Integer id : idList){
            passList.add(pdao.findById(id).orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.OK).body(passList);
    }

    @Override
    public ResponseEntity<?> getAvailableSeats(Integer flightId) {
        List<SeatDetails> seat = seatDao.findByIsAvailableAndFlightId(true, fDao.findFlightById(flightId));
        List<SeatDTO> seatList = new ArrayList<>();
        for(SeatDetails s : seat){
            seatList.add(modelMapper.map(s, SeatDTO.class));
        }
        System.out.println("\n\n\n LIST: " + seatList + "\n\n\n");
        return ResponseEntity.ok().body(seatList);
    }

    @Override
    public ResponseEntity<?> makePayment(PaymentDTO paymentDTO) {
        System.out.println("\n\n" + paymentDTO + "\n\n");
        PaymentDetails payment = new PaymentDetails();
        UserDetailsEntity user = udao.findById(paymentDTO.getCustomerId()).orElseThrow();
        System.out.println("\n\n" + user.getPaymentID().size() + "\n\n");
        payment.setCustomerId(user);
        payment.setStatus(StatusEnum.Successful);
        System.out.println("\n\n" + "total amt: " + paymentDTO.getTotalAmount() + "\n\n");
        payment.setTotalAmount(paymentDTO.getTotalAmount());
        paydao.save(payment);
        return ResponseEntity.ok().body(payment.getId());
    }

}
