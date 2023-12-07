package com.med.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.exception.MedicineException;
import com.med.model.Medicine;
import com.med.repository.MedicineRepository;
import com.med.serviceinetrface.MedicineInterface;


@Service
public class MedicineImplementation implements MedicineInterface{

	@Autowired
	private MedicineRepository medicineRepository;
	
	@Override
	public Medicine addMedicine(Medicine medicine) {
		Medicine existingMedicine= medicineRepository.findByMedicineName(medicine.getMedicineName());
		Medicine existingMedicine1=medicineRepository.findByCompanyName(medicine.getCompanyName());
		if(existingMedicine !=null && existingMedicine1 !=null) {
			throw new MedicineException("Medicine already exist by this name and company");
		}
		return medicineRepository.save(medicine);
	}

	@Override
	public Medicine updateMedicine(Integer medicineId, Medicine medicine) {
		
		Optional<Medicine> medicineOptional=medicineRepository.findById(medicineId);
		if(medicineOptional.isEmpty()) {
			throw new MedicineException("Medicine not found for this id");
		}
		medicineOptional.get().setMedicineName(medicine.getMedicineName());
		medicineOptional.get().setMedicineDescription(medicine.getMedicineDescription());
		medicineOptional.get().setCompanyName(medicine.getCompanyName());
		
		return medicineRepository.save(medicineOptional.get());
	}

	@Override
	public List<Medicine> findAll() {
		
		return medicineRepository.findAll();
	}

	@Override
	public Medicine findById(Integer medicineId) {
		
	Optional<Medicine> medicine=	medicineRepository.findById(medicineId);
	if(medicine.isEmpty()) {
		throw new MedicineException("Medicine not found for this MedicineId");
	}
	return medicine.get();
	}

	@Override
	public Medicine findByName(String medicineName) {
		
	Medicine medicine=medicineRepository.findByMedicineName(medicineName);
		if(medicine ==null) {
			throw new MedicineException("Medicine not exist by this name");
		}
		
		return medicine;
	}
	
	

	@Override
	public List<Medicine> findByCategory(String medicineCategory) {
		List<Medicine> medicine=medicineRepository.findByCategory(medicineCategory);
		if(medicine==null) {
			throw new MedicineException("Medicine not exist in this category");
		}
		return medicine;
	}

	@Override
	public String deleteMedicine(Integer medicineId) {
		
		Optional<Medicine> medicine=medicineRepository.findById(medicineId);
		
		if(medicine.isPresent()) {
			medicineRepository.delete(medicine.get());
			
			return "Deleted Successfully";
		}
		else {
			throw new MedicineException("Medicine not present for this id");
		}
		
	}

}
