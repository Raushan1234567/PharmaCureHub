package com.med.serviceinetrface;

import java.util.List;

import com.med.model.Customer;


public interface CustomerInterface {

	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Integer customerId, String firstName,String lastName,String password, String address);

	public Customer findByEmail(String customerEmail);
	
	public List<Customer> findAllCustomer();
	
	public Customer deleteCustomerById(Integer customerId);
	
	
	
}
