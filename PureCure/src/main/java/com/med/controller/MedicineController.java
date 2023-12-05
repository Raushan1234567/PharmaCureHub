package com.med.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.Medicine;
import com.med.serviceinetrface.MedicineInterface;

@RequestMapping("/medicine")
@RestController
public class MedicineController {
	
	@Autowired
	private MedicineInterface  medicineInterface; 
	
	@PostMapping("/adds")
	public ResponseEntity<Medicine> addMedicne(@RequestBody Medicine medicne){
		return new ResponseEntity<Medicine>(medicineInterface.addMedicine(medicne), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{medicineId}")
	public ResponseEntity<Medicine> findById(@PathVariable Integer medicineId){
		return new ResponseEntity<Medicine>(medicineInterface.findById(medicineId),HttpStatus.OK);
		
	}
	
	@GetMapping("/name/{medicineName}")
	public ResponseEntity<Medicine> findByName(@PathVariable String medicineName){
		return new ResponseEntity<Medicine>(medicineInterface.findByName(medicineName), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/category/{medicineCategory}")
	public ResponseEntity<List<Medicine>> findByCategory(@PathVariable String medicineCategory){
		return new ResponseEntity<List<Medicine>>(medicineInterface.findByCategory(medicineCategory), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Medicine>> findAll(){
		return new ResponseEntity<List<Medicine>>(medicineInterface.findAll(),HttpStatus.ACCEPTED);
		
	}
	
	@PatchMapping("/update/{medicineId}")
	public ResponseEntity<Medicine> updateMedicine(@PathVariable Integer medicineId,@RequestBody Medicine medicine){
		return new ResponseEntity<Medicine>(medicineInterface.updateMedicine(medicineId, medicine),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{medicineId}")
	public ResponseEntity<String> deleteMedicine(@PathVariable Integer medicineId) {
		return new ResponseEntity<String>(medicineInterface.deleteMedicine(medicineId),HttpStatus.OK);
	}

}
