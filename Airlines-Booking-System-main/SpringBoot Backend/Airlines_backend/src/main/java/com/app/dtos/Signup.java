package com.app.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.entities.AddressDetails;
import com.app.entities.GenderEnum;
import com.app.entities.UserDetailsEntity;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Signup {
	@JsonProperty(access = Access.READ_ONLY) // this property only used during ser.
	private Long id;
	@NotBlank(message = "First Name required")
	private String firstName;
	@Email(message = "Invalid Email!!!")
	private String email;
	private String password;
	private UserRole role;
	private String aadhar;
    private LocalDate dob;
    private GenderEnum gender;
    private String mobileNumber;
    private AddressDetails pincode;
    private UserDetailsEntity customer;
    private String address;
	public Signup(String firstName, 
			String email, String password, UserRole role) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
}
