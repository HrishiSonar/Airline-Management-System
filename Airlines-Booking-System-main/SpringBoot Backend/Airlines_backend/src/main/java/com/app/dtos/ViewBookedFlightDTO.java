package com.app.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ViewBookedFlightDTO {
    private String flightName;
    private String customerName;
    private String source;
    private String destination;
    private double totalAmount;
    private String seatNo;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private LocalTime duration;
}
