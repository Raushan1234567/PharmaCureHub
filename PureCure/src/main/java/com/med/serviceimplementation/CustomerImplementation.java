package com.med.serviceimplementation;

import java.util.Optional;

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

	@Override
	public Customer upadateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
