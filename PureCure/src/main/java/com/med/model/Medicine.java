package com.med.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medicineId;
	
	private String medicineName;
	
	private double price;
	
	private String image;
	
	private int numberOfTablets;
	
	private String medicineDescription;
	
	private String medicineManufacturingDate;
	
	private String medicineExpiryDate;
	
	private String companyName;
	
	private String category;
	
	
	
//	@JsonIgnore
//    @ManyToMany
//    private List<Cart> carts = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

	
}
