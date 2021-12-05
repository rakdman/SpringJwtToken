package com.rakdman.jwtdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakdman.jwtdemo.jwtutil.JWTUtility;
import com.rakdman.jwtdemo.model.JwtRequest;
import com.rakdman.jwtdemo.model.JwtResponse;
import com.rakdman.jwtdemo.service.UserService;

@RestController
public class HomeController {
	
	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "Welcome to Home";
	}
	
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		try {
		 	authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
			
		} catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS",e);
		}
		
		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
		
		final String token = jwtUtility.generateToken(userDetails);
		
		return new JwtResponse(token);
		
		
		
	}
	
}
