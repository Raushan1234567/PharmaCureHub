package com.med.serviceimplementation;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.exception.CustomerException;
import com.med.model.Customer;
import com.med.repository.CustomerRepository;
import com.med.serviceinetrface.CustomerInterface;


@Service
public class CustomerImplementation implements CustomerInterface{

	@Autowired
	private CustomerRepository customerrepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> cust=customerrepository.findBycustomerEmail(customer.getCustomerEmail());
		if(cust.isPresent()) {
			
		throw new CustomerException("already exist");
		}
		return customerrepository.save(customer);
	}

	public Customer updateCustomer(Integer customerId, String firstname,String lastname,String adress,String password) {
        Optional<Customer> existingCustomerOptional = customerrepository.findById(customerId);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
             
            existingCustomer.setCustomerfirstName(firstname);
            existingCustomer.setCustomerlastName(lastname);
            existingCustomer.setCustomerAddress(adress);
           
            existingCustomer.setCustomerPassword(password);
        
            return customerrepository.save(existingCustomer);
        } else {
            // Handle customer not found
            throw new CustomerException("Customer not found for this id");
        }
    }

	
	public Customer FindById(Integer customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> customerFinfById=customerrepository.findById(customerId);
		if(customerFinfById.isPresent()) {
			return customerFinfById.get();
		}
		else {
			throw new CustomerException("Customer Not found");
		}
	}

}
