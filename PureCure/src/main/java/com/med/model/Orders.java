package com.med.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int orderId;
	    
	    private Date orderDate;
	    
	    private String orderName;
	    
	   
	    
	    
	    @ManyToOne
	    @JoinColumn(name = "customer_id" )
	    private Customer customer;
}
