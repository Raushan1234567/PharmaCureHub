package com.med.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.med.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>,PagingAndSortingRepository<Medicine, Integer>{


	   public List<Medicine> findByMedicineName(String medicineName);

	    public List<Medicine> findByCategory(String category);

	    public List<Medicine> findByCompanyName(String companyName);
}
