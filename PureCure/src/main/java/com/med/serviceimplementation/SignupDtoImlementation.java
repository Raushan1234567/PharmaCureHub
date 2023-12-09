package com.med.serviceimplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.exception.CustomerException;
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
	     
        	Optional<Customer> customer=customerRepository.findBycustomerEmail(signupDto.getCustomerEmail());
        	if(customer.isEmpty()) {
	        Customer newCustomer = new Customer();
	        newCustomer.setCustomerfirstName(signupDto.getCustomerfirstName());
	        newCustomer.setCustomerEmail(signupDto.getCustomerEmail());
	        newCustomer.setCustomerPassword(signupDto.getCustomerPassword());

	        customerRepository.save(newCustomer);
        	}
        	else {
        		throw new CustomerException("Alraedy have an acount");
        	}

	    }
}
