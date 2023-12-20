package com.med.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{


	   public List<Medicine> findByMedicineName(String medicineName);

	    public List<Medicine> findByCategory(String category);

	    public List<Medicine> findByCompanyName(String companyName);
}
