package com.app.daos;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.GeneralDetails;

public interface GeneralDtlsDao extends JpaRepository<GeneralDetails,Integer> {

    @Query(value = "select * from general_details where customer_id=:id",nativeQuery = true)
    GeneralDetails findByCustomerId(@Param("id")Integer id);

    @Modifying
    @Query(value="update general_details set aadhar=:aadhar, address=:address, dob=:dob ,gender=:gender,mobile_number=:mobileNumber,pincode_pincode=:pincode",nativeQuery = true)
    void editProfile(@Param("aadhar")String aadhar, @Param("address")String address, @Param("dob")LocalDate dob, @Param("gender")String gender, @Param("mobileNumber")String mobileNumber,
            @Param("pincode") int pincode);
    
}
