package com.med.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
	private String customerfirstName;
	
	private String customerlastName;
	
	private String customerEmail;
	
	private String customerPassword;
	
	private String customerMobileNumber;
	
	private String customerAddress;
	
	@OneToMany
	private List<Orders> orderList1=new ArrayList<>();
	
	@OneToMany
	private List<Medicine> medicineList1=new ArrayList<>();
	
}
