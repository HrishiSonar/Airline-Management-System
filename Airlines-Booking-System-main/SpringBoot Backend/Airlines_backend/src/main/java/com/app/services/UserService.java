package com.app.services;

import com.app.dtos.Signup;

public interface UserService {
//sign up
	Signup userRegistration(Signup reqDTO);
}