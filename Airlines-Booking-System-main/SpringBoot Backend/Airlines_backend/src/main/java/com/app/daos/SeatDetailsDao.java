package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.BookingDetails;
import com.app.entities.FlightDetails;
import com.app.entities.SeatDetails;


public interface SeatDetailsDao extends JpaRepository<SeatDetails, Integer>{
    List<SeatDetails> findByIsAvailableAndFlightId(boolean isAvailable, FlightDetails bookingId);


    @Modifying
    @Query(value = "update seat_details set is_available = 0, booking_id = :bookingId where flight_id = :flightId and seat_no = :seatNo", nativeQuery = true)
    int updateSeatDetails(@Param("flightId") Integer flightId,@Param("seatNo") String seatNo, @Param("bookingId") Integer bookingId);


    List<SeatDetails> findByBookingId(BookingDetails bookingId);
}
