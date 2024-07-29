package com.app.dtos;

import java.time.LocalDateTime;

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
public class FlightDetailsDTO {
    @JsonProperty("toCity")
    private String toCity;
    @JsonProperty("fromCity")
    private String fromCity;
    @JsonProperty("departure")
    private LocalDateTime departure;
}
