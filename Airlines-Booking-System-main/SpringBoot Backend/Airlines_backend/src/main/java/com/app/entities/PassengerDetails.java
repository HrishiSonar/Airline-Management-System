package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import lombok.AllArgsConstructor;
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
public class PassengerDetails extends BaseEntity{
    
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
	private LocalDate dob;

    @Column(nullable = false, length = 12)
	private String aadhar;

    @Enumerated(EnumType.STRING)
	private GenderEnum gender;
}
