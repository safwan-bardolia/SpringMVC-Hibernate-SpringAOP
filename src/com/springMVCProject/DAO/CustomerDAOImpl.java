package com.springMVCProject.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springMVCProject.Entity.Customer;


//@Repository means this class also register as a bean along with its own specialization
@Repository	
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get current session
		Session session = sessionFactory.getCurrentSession();

		// create query //name of the class otherwise it throws error 
		//remember we have to use 'class name' in query i.e 'from Customer'
		//sort by lastname (remember l_name is a field of class)
		Query<Customer> q1 = session.createQuery("from Customer order by l_name", Customer.class);  // org.hibernate.query.Query
																									// generic to query introduced since
																									// 'Hibernate 5.2'
		// execute query & get result list
		List<Customer> customers = q1.getResultList();

		// return list
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {

		//get the current session
		Session session=sessionFactory.getCurrentSession();
		
		//fetch record using primary key
		Customer customer=session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		//get the current session
		Session session=sessionFactory.getCurrentSession();
		
		//first fetch the record from DB
		Customer customer=session.get(Customer.class, id);
		
		//remove the record
		session.remove(customer);
	}

}
