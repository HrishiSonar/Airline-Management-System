package com.app.services;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.daos.UserDao;
import com.app.entities.UserDetailsEntity;

@Service
@Transactional
public class CustomeUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userdao;
    

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetailsEntity user = userdao.findByEmail(email);
		System.out.println("---------------------"+user);
		return new CustomUserDetails(user);
	}

    
    
}
