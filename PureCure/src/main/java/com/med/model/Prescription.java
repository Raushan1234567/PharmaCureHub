package com.med.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Prescription {
	 @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prescriptionId;
     private String name;
	
   
	private String type;

	@Lob
	@Column(name = "imagedata", columnDefinition="LONGBLOB")
	private byte[] imageData;
	
	
	
}
