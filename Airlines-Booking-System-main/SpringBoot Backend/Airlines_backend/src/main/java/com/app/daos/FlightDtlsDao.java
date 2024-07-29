package com.app.daos;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.FlightDetails;

public interface FlightDtlsDao extends JpaRepository<FlightDetails, Integer> {
    @Query(value = "select * from flight_details where source = :fromCity and destination = :toCity and departure >= :departure", nativeQuery = true)
    List<FlightDetails> findByCityAndDate(@Param("toCity") String toCity,@Param("fromCity") String fromCity, @Param("departure") LocalDateTime departure);

    @Query(value="select * from flight_details where id=:id",nativeQuery = true)
    FlightDetails findFlightById(@Param("id")Integer id);

    @Modifying
    @Query(value="update flight_details set arrival=:arrival,departure=:departure,destination=:destination,flight_class=:flight_class,name=:name,source=:source, fare_price=:farePrice where id=:id",nativeQuery = true)
    Integer editFlight(@Param("arrival")LocalDateTime arrival, @Param("departure")LocalDateTime departure, @Param("destination")String destination, @Param("flight_class")String flight_class, @Param("name")String name, @Param("source")String source,@Param("id")Integer id, @Param("farePrice") double farePrice);

}
