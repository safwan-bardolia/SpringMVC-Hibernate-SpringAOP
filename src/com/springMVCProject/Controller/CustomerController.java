package com.springMVCProject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springMVCProject.Entity.Customer;
import com.springMVCProject.Service.CustomerService;



@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject customer DAO
	@Autowired									
	private CustomerService customerService;			//spring will scan for a component that implements CustomerDAO interface	
												//& using @Repository it able to find implementation and inject it	
												
	
	@GetMapping("/list")						//it handles only get Request
	public String listCustomer(Model model){

		//get customer list
		List<Customer>customers=customerService.getCustomers();		//delegate call to service layer
		
		//add list to model
		model.addAttribute("customers", customers);
		
		return "list-customer";
	}
	
	@GetMapping("/AddCustomer")
	public String addCustomer(Model model) {
		
		Customer customer=new Customer();	//for binding customer object to html form
		
		model.addAttribute("customer",customer);
		
		return "add-customer";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		
		StringTrimmerEditor editor=new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, editor);
	}
	
	@PostMapping("/validateForm")	
	public String validateForm(@Valid @ModelAttribute Customer customer,BindingResult bindingResult,Model model) {	//@valid tell the spring to validate
																												//cutomer object			
		System.out.println(bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "add-customer";
		}
		else {
		
			customerService.saveCustomer(customer);
			
			
			return "redirect:/customer/list";		//use of redirect to transfer a http request to another page. 
		}
	}
	
	
	@GetMapping("/UpdateCustomer")
	public String updateCustomer(@RequestParam("customerId") int id ,Model model) {
		
		//get-the current customer from DB for pre-populating form
		Customer customer=customerService.getCustomer(id);
		
		//add that customer to model attribute
		model.addAttribute("customer",customer);
		
		//and send to our form
		return "add-customer";
	}
	
	@GetMapping("/DeleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
}
