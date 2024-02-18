package com.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.model.Orders;

public interface OrderRepository extends JpaRepository<Orders,Integer>{

}
