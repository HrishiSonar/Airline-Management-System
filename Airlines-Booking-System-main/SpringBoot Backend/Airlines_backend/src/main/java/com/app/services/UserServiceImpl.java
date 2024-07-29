package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.GeneralDtlsDao;
import com.app.daos.UserDao;
import com.app.dtos.Signup;
import com.app.entities.GeneralDetails;
import com.app.entities.RoleEnum;
import com.app.entities.UserDetailsEntity;
import com.app.entities.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private GeneralDtlsDao generalDao;
	//dep : dao layer i/f
	@Autowired
	private UserDao userDao;
	//dep
	@Autowired
	private ModelMapper mapper;
	//dep 
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Signup userRegistration(Signup reqDTO) {
		//dto --> entity
		System.out.println("dtooooooo "+reqDTO.getPassword());
		UserDetailsEntity user=new UserDetailsEntity();
		user.setEmail(reqDTO.getEmail());
		user.setName(reqDTO.getFirstName());
		user.setRole(reqDTO.getRole());
		user.setCpass(encoder.encode(reqDTO.getPassword()));//pwd : encrypted using SHA
		System.out.println("in service---------"+user.getCpass());
		UserDetailsEntity u=userDao.save(user);
		if(reqDTO.getRole() == UserRole.ROLE_USER){
			GeneralDetails general = new GeneralDetails();
			general.setAadhar(reqDTO.getAadhar());
			general.setAddress(reqDTO.getAddress());
			general.setCustomer(u);
			general.setDob(reqDTO.getDob());
			general.setGender(reqDTO.getGender());
			general.setMobileNumber(reqDTO.getMobileNumber());
			general.setPincode(reqDTO.getPincode());
			generalDao.save(general);
		}
		return new Signup(u.getName(),u.getEmail(), u.getCpass(), u.getRole());
	}

}
