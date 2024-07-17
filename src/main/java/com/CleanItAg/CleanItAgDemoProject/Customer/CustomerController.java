package com.CleanItAg.CleanItAgDemoProject.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@RequestMapping(value="/customers", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return ResponseEntity.ok(customers);
	}
	
	@RequestMapping(value="/customers/id/{customerid}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomersById(@PathVariable int customerid) {
		Optional<Customer> customer = customerRepository.findById(customerid);
		if (customer.isEmpty()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(customer.get());
	}
}
