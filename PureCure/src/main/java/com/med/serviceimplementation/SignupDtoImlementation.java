package com.med.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.model.Customer;
import com.med.model.SignupDto;
import com.med.repository.CustomerRepository;
import com.med.serviceinetrface.SignupDtoInteface;

@Service
public class SignupDtoImlementation implements SignupDtoInteface{
	
	 @Autowired
	 private CustomerRepository customerRepository;

        @Override
	    public void signUpUser(SignupDto signupDto) {
	     
	        Customer newCustomer = new Customer();
	        newCustomer.setCustomerfirstName(signupDto.getCustomerFirstName());
	        newCustomer.setCustomerEmail(signupDto.getCustomerEmail());
	        newCustomer.setCustomerPassword(signupDto.getCustomerPassword());

	        customerRepository.save(newCustomer);

	    }
}
