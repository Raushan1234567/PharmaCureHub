package com.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
