package com.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.Customer;
import com.med.serviceinetrface.CustomerInterface;

@RestController
public class customerController {

	@Autowired
	private CustomerInterface customerInterface;
	
	@PostMapping("/AddCustomers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerInterface.createCustomer(customer),HttpStatus.CREATED);
		
	}
	
}
