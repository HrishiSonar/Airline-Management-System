package com.app.dtos;

import java.time.LocalDate;


import com.app.entities.AddressDetails;
import com.app.entities.GenderEnum;
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
public class ViewProfileDTO {
    @JsonProperty("customerId")
    private Integer customerId;

    @JsonProperty("dob")
    private LocalDate dob;
    @JsonProperty("address")
	private String address;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("aadhar")
	private String aadhar;
    @JsonProperty("gender")
	private GenderEnum gender;
	@JsonProperty("pincode")
	private AddressDetails pincode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cpass")
	private String cpass;
}
