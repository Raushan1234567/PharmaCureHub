package com.med.serviceinetrface;

import java.util.List;

import com.med.model.Medicine;

public interface MedicineInterface {

	public Medicine addMedicine(Medicine medicine);
	
	public Medicine updateMedicine(Integer medicineId,Medicine medicine);
	
	public List<Medicine> findAll();
	
	public Medicine findById(Integer medicineId);
	
	public List<Medicine> findByName(String medicineName);
	
	public List<Medicine> findByCategory(String medicineCategory);
	
	

	public String deleteMedicine(Integer medicineId);
	
}
