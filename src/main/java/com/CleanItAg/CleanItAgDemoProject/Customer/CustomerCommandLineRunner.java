package com.CleanItAg.CleanItAgDemoProject.Customer;


import com.CleanItAg.CleanItAgDemoProject.Customer.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CustomerCommandLineRunner implements CommandLineRunner {

	private CustomerRepository customerRepository;

	public CustomerCommandLineRunner(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@Override
	public void run(String... args) throws Exception{
		customerRepository.save(new Customer(1, "Albert 1", "customer1@mail.com", "customer1 street nr city code", "+41 123 456 789"));
		customerRepository.save(new Customer(2, "Berta 2", "customer2@mail.com", "customer2 street nr city code", "+42 123 456 789"));
		customerRepository.save(new Customer(3, "Charles 3", "customer3@mail.com", "customer3 street nr city code", "+43 123 456 789"));
		customerRepository.save(new Customer(4, "Denise 4", "customer4@mail.com", "customer4 street nr city code", "+44 123 456 789"));
		customerRepository.save(new Customer(5, "Elise 5", "customer5@mail.com", "customer5 street nr city code", "+45 123 456 789"));
	}
}
