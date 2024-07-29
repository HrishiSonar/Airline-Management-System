package com.app.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.SigninRequest;
import com.app.dtos.SigninResponse;
import com.app.dtos.Signup;
import com.app.entities.GeneralDetails;
import com.app.entities.UserDetailsEntity;
import com.app.config.JwtUtils;
import com.app.daos.GeneralDtlsDao;
import com.app.daos.UserDao;
import com.app.services.CustomeUserDetailsServiceImpl;
import com.app.services.UserService;

@RestController
@RequestMapping("/jwt")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.TRACE })
public class UserSignInSignUpController {

	@Autowired 
	private HttpSession session;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private JwtUtils utils;//token manager

	@Autowired 
	private AuthenticationManager mgr;

	// sign up
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody Signup dto) {
		System.out.println("in sign up " + dto.getEmail()+"---"+dto.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegistration(dto));
	}

	/*
	 * request payload : Auth req DTO : email n password resp payload : In case of
	 * success : Auth Resp DTO : mesg + JWT token + SC 200 IN case of failure : SC
	 * 401
	 */
	@PostMapping("/signin")
		public ResponseEntity<?> signinUser(@RequestBody SigninRequest reqDTO) {
			// System.out.println("in signin " + reqDTO.getEmail());
			// simply invoke authentucate(...) on AuthMgr
			// i/p : Authentication => un verifed credentials
			// i/f --> Authentication --> imple by UsernamePasswordAuthToken
			// throws exc OR rets : verified credentials (UserDetails i.pl class: custom
			// user details)

			Authentication verifiedAuth = mgr
					.authenticate(new UsernamePasswordAuthenticationToken
							(reqDTO.getEmail(), reqDTO.getPassword()));
			// System.out.println(verifiedAuth.getClass());// Custom user details
			// => auth success
			// System.out.println("here token    ------------"+utils.generateJwtToken(verifiedAuth));
			UserDetailsEntity u=userdao.findByEmail(reqDTO.getEmail());
			session.setAttribute("userid", u.getId());
			// System.out.println("email mila  "+ (Integer)session.getAttribute("userid"));
			return ResponseEntity
					.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), u.getId()));

		}

}