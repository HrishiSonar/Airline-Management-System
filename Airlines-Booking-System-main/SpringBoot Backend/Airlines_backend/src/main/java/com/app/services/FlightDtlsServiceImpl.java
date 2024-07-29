package com.app.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.daos.AddressDtlsDao;
import com.app.daos.FlightDtlsDao;
import com.app.daos.GeneralDtlsDao;
import com.app.daos.UserDao;
import com.app.dtos.AddUserDTO;
import com.app.entities.FlightDetails;
import com.app.entities.GeneralDetails;
import com.app.entities.UserDetailsEntity;


@Service
@Transactional
public class FlightDtlsServiceImpl implements FlightDtlsService {

	@Autowired
	AddressDtlsDao addressDtlsDao;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private FlightDtlsDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private GeneralDtlsDao generalDtlsDao;
	
	@Override
	public List<FlightDetails> getAllFlights() {
		return dao.findAll();
	}



	@Override
	public ResponseEntity<List<FlightDetails>> getCustomeFlightDetails(String toCity, String fromCity, LocalDateTime departure) {
		List<FlightDetails> list = dao.findByCityAndDate(toCity, fromCity, departure);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		//System.out.println(list);
	}



	@Override
	public ResponseEntity<?> addUser(AddUserDTO user) {
		UserDetailsEntity userDetails = modelMapper.map(user, UserDetailsEntity.class);
		GeneralDetails generalDetails = modelMapper.map(user, GeneralDetails.class);
		userDao.save(userDetails);
		generalDetails.setCustomer(userDetails);
		System.out.println("\n\n USER ADDED \n\n");
		System.out.println("\n\n" + userDetails + "\n\n");
		System.out.println("\n\n ADDING General DETAILS \n\n");
		generalDtlsDao.save(generalDetails);
		
		System.out.println("\n\n" + generalDetails + "\n\n");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}



	@Override
	public ResponseEntity<?> getPincodes() {
		return ResponseEntity.ok().body(addressDtlsDao.findAll());
	}



	@Override
	public ResponseEntity<?> getFlightById(Integer id) {
		FlightDetails temp = dao.findById(id).orElseThrow();
		return ResponseEntity.status(HttpStatus.OK).body(temp);
	}

	

}
