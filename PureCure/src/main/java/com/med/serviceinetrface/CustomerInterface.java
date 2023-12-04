package com.med.serviceinetrface;

import com.med.model.Customer;


public interface CustomerInterface {

	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Integer customerId, String firstName,String lastName,String password, String address);
	
	
	
}
