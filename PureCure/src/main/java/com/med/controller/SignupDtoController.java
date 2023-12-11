package com.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.SignupDto;
import com.med.serviceinetrface.SignupDtoInteface;

@CrossOrigin(origins = "*")
@RestController
public class SignupDtoController {

	@Autowired
	private SignupDtoInteface signupDtoInteface;
	

	 @PostMapping("/signup")
	    public ResponseEntity<String> signUpUser(@RequestBody SignupDto signupDto) {
		 signupDtoInteface.signUpUser(signupDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful!");
	       
	    }
	
}
