package com.med.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.med.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Query("SELECT c.id FROM Cart c WHERE c.customer.customerId = :customerId")
    Optional<Integer> findCartIdByCustomerId(@Param("customerId") Integer customerId);

}
