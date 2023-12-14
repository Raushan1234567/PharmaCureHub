
package com.med.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Orders> orderList1=new ArrayList<>();
	
	  @JsonIgnore
	    @OneToMany(cascade = CascadeType.ALL)
	    private List<Medicine> medicineList = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Cart cart;

	
}
