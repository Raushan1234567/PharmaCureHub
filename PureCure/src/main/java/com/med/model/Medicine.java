package com.med.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private String medicineDescription;
	
	private String medicineManufacturingDate;
	
	private String medicineExpiryDate;
	
	private String medicineCompanyName;
	
}
