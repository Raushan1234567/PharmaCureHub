package com.med.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.model.Prescription;


public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	Optional<Prescription> findByName(String fileName);
}
