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
public class FlightDTO {
	@JsonProperty("name")
    private String name;
	@JsonProperty("flight_class")
	private String flightClass;
	
	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("departure")
	private LocalDateTime departure;
	
	@JsonProperty("arrival")
	private LocalDateTime arrival;

	@JsonProperty("farePrice")
	private double farePrice;
}

