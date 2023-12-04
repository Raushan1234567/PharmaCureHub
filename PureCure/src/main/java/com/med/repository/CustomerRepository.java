package com.med.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	

	Optional<Customer> findBycustomerEmail(String customerEmail);

}
