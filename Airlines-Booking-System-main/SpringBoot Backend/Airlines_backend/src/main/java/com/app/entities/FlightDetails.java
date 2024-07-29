package com.app.entities;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FlightDetails extends BaseEntity {
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String flightClass;
	
	@Column(nullable = false, length = 100)
	private String destination;
	
	@Column(nullable = false, length = 100)
	private String source;
	
	@Column(nullable = false)
	private LocalDateTime departure;
	
	@Column(nullable = false)
	private LocalDateTime arrival;

	private double farePrice;

}
