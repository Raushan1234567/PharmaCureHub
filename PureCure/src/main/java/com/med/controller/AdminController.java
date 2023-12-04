package com.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.exception.AdminAlreadyExistsException;
import com.med.model.Admin;
import com.med.model.Customer;
import com.med.serviceinetrface.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/addAdmins")
	public ResponseEntity<Admin> addNewAdmin(@RequestBody Admin admin) throws AdminAlreadyExistsException{
		
	 	Admin ad = adminService.addNewAdmin(admin);
	 	
	 	return new ResponseEntity<Admin>(ad, HttpStatus.CREATED);
	}
	


}
