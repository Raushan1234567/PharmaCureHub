package com.med.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.exception.AdminAlreadyExistsException;
import com.med.model.Admin;
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
	
	@PatchMapping("/updateAdmins")
	public ResponseEntity<Admin> updateAdminDetails(@RequestBody Admin admin){
		Admin admin1 = adminService.updateAdmin(admin);
		return new ResponseEntity<Admin>(admin1, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAdmins/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer adminId){
		Admin newAdmin = adminService.deleteAdminById(adminId);
		return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
	}
	

	@GetMapping("getAdmins/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Integer adminId){
		Admin newAdmin = adminService.findAdminById(adminId);
		return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
	}
	
	@GetMapping("getAllAdmins")
	public ResponseEntity<List<Admin>> getAdminById(){
		 List<Admin> adminList = adminService.findAllAdmin();
		return new ResponseEntity<>(adminList, HttpStatus.OK);
	}
}
