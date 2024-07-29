package com.app.dtos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookFlightDTO {
    
    @JsonProperty("cid")
    private Integer cid;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("flightID")
    private Integer flightID;

    @JsonProperty("paymentId")
    private Integer paymentId;

    @JsonProperty("seatNo")
    private List<String> seatNo = new ArrayList<>();

    @JsonProperty("passengerId")
    private List<Integer> passengerid = new ArrayList<>();

    @JsonProperty("passengerNames") // this is only for view booked tickets
    private List<String> passangerNames = new ArrayList<>();
    
}
