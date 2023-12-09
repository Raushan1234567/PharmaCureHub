package com.med.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String customerFirstName;
    private String customerEmail;
    private String customerPassword;
    
    // Constructors if needed
}
