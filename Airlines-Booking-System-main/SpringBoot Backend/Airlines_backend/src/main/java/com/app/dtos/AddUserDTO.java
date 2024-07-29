package com.app.dtos;

import java.time.LocalDate;

import com.app.entities.AddressDetails;
import com.app.entities.GenderEnum;
import com.app.entities.RoleEnum;
import com.app.entities.UserDetailsEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserDTO {
    private String name;
    private String cpass;
    private String email;
    private RoleEnum role;
    private String aadhar;
    private LocalDate dob;
    private GenderEnum gender;
    private String mobileNumber;
    private AddressDetails pincode;
    private UserDetailsEntity customer;
    private String address;
}
