package com.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.Customer;
import com.med.serviceimplementation.CustomerImplementation;
import com.med.serviceinetrface.CustomerInterface;

import lombok.experimental.PackagePrivate;



@RestController
public class customerController {

	@Autowired
	private CustomerInterface customerInterface;
	
	@Autowired
	private CustomerImplementation customerImplementation;
	
	@PostMapping("/AddCustomers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerInterface.createCustomer(customer),HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/customers/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerInterface.updateCustomer(customerId, customer.getCustomerfirstName(),customer.getCustomerlastName(),customer.getCustomerAddress(),customer.getCustomerPassword()),HttpStatus.CREATED);

		
	}
	
	@GetMapping("/findById/{customerId}")
	public ResponseEntity<Customer> FindById(@PathVariable Integer customerId){
		return new ResponseEntity<Customer>(customerImplementation.FindById(customerId),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findbyemail/{customerEmail}")
	public ResponseEntity<Customer> findbyemail(@PathVariable String customerEmail){
		return new ResponseEntity<Customer>(customerInterface.findByEmail(customerEmail),HttpStatus.OK);
		
	}
	
	
}
