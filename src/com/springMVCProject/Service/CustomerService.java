package com.springMVCProject.Service;

import java.util.List;

import javax.validation.Valid;

import com.springMVCProject.Entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int id);
	
	public void deleteCustomer(int id);
}
