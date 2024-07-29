package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.AddressDetails;

public interface AddressDtlsDao extends JpaRepository<AddressDetails,Integer>{
    
}
