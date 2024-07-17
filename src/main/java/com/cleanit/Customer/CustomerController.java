package com.cleanit.Customer;

import java.util.List;
import java.util.Optional;

import com.cleanit.Customer.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	private final CustomerRepository customerRepository;

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
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
