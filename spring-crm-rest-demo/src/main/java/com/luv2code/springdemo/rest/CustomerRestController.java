package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService

	@Autowired
	private CustomerService customerService;

	// add mapping for GET /customers

	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerService.getCustomers();
	}

	// add mapping for GET /customers/{customerId}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		return theCustomer;
	}

	// add mapping for POST /customers - add new customer

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// set id to 0 because it's a primary-key. saveOrUpdare method INSERT a value if
		// primary key it's null or 0. If primary-key is specified, it will UPDATE it;
		// also just in case it was passed an id in JSON ... set it to 0
		// this is to force a save of new item ... instead of update
		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	// add mapping for PUT /customers - update existing customer

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	// add mapping for DELETE /customers/{customerId} - delete existing customer

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		// throw exception if id doesn't exist
		Customer theCustomer = customerService.getCustomer(customerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		customerService.deleteCustomer(customerId);

		return "Deleted customer id - " + customerId;
	}

}