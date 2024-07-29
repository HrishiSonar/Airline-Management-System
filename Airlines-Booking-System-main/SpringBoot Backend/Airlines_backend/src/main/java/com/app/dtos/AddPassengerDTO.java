package com.app.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.app.entities.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@AllArgsConstructor
public class AddPassengerDTO {
    @JsonProperty("customerId")
    @NonNull
    private Integer customerId;
    @JsonProperty("dob")
    private LocalDate dob;
    @JsonProperty("address")
	private String address;
	@JsonProperty("mobileNumber")
    @Length(max = 10, min = 10)
	private String mobileNumber;
	@JsonProperty("aadhar")
    @NonNull
    @Length(min = 12, max = 12)
	private String aadhar;
    @JsonProperty("gender")
	private GenderEnum gender;
	@JsonProperty("pincode")
	private int pincode;
    @JsonProperty("bookingId")
    private Integer bookingid;
    @JsonProperty("name")
    @NotBlank
    private String name;
}
