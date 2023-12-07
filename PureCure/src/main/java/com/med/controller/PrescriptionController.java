package com.med.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.med.serviceimplementation.PrescriptionServiceImplementation;


@RestController
@RequestMapping("/images")
public class PrescriptionController {

	@Autowired
	private PrescriptionServiceImplementation im; 
	
	@PostMapping("/add/prescriptions")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException{
		String uploadImage=im.uploadImage(file);
		
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
		
		
		
	}
	
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> download(@PathVariable String fileName){
		
		byte []  ima=im.downloadImage(fileName);
		
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(ima);
		
		
		
	}
	
}
