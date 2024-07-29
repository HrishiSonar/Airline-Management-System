package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.PaymentDetails;
import com.app.entities.UserDetailsEntity;

public interface PaymentDtlsDao extends JpaRepository<PaymentDetails,Integer>{

    @Query(value="select * from payment_details",nativeQuery = true)
    List<PaymentDetails> getAllPayments();
    
    @Query(value="select * from payment_details where id=:pid",nativeQuery = true)
    PaymentDetails findPaymentById(@Param("pid")Integer paymentId);

    List<PaymentDetails> findByCustomerId(UserDetailsEntity customerId);
}
