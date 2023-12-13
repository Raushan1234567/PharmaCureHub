package com.med.serviceimplementation;

import java.util.List;
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
           
            throw new CustomerException("Customer not found for this id");
        }
    }

	
	public Customer FindById(Integer customerId) {
		
		Optional<Customer> customerFinfById=customerrepository.findById(customerId);
		if(customerFinfById.isPresent()) {
			return customerFinfById.get();
		}
		else {
			throw new CustomerException("Customer Not found");
		}
	}

	@Override
	public Customer findByEmail(String customerEmail) {
		// TODO Auto-generated method stub
	Optional<Customer> customer=	customerrepository.findBycustomerEmail(customerEmail);
		if(customer.isPresent()) {
			return customer.get();
			
		}else {
			
			throw new CustomerException("Customer Not found");
		}
	}

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> custList = customerrepository.findAll();
		return custList;
	}

	@Override
	public Customer deleteCustomerById(Integer customerId) {
		Customer customer = customerrepository.findById(customerId).get();
		if(customer != null) customerrepository.delete(customer);
		else throw new CustomerException("Customer doesn't exist");
		// TODO Auto-generated method stub
		return customer;
	}

	

}
