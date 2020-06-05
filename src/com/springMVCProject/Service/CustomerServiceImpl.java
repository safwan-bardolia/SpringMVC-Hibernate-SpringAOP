package com.springMVCProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springMVCProject.DAO.CustomerDAO;
import com.springMVCProject.Entity.Customer;

@Service	//it simply delegate call to DAO
public class CustomerServiceImpl implements CustomerService {

	//need to inject customerDAO
	@Autowired
	private CustomerDAO cutomerDAO;
	
	@Override
	@Transactional // no need to explicit define open() & commit() transaction
	public List<Customer> getCustomers() {
		return cutomerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		cutomerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {		
		return cutomerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		cutomerDAO.deleteCustomer(id);
	}

	
}
