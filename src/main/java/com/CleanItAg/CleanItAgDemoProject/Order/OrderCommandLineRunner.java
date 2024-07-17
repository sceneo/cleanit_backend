package com.CleanItAg.CleanItAgDemoProject.Order;


import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.CleanItAg.CleanItAgDemoProject.Customer.CustomerRepository;
import com.CleanItAg.CleanItAgDemoProject.Employee.EmployeeRepository;

@Component
@Order(3)
public class OrderCommandLineRunner implements CommandLineRunner {

	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	private EmployeeRepository employeeRepository;

	public OrderCommandLineRunner(OrderRepository orderRepository ,CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
		super();
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public void run(String... args) throws Exception{
		orderRepository.save(new OrderService(employeeRepository.findById((long) 1).get(), "Suit and Shoes 1", LocalDateTime.now().plusDays(2), customerRepository.findById(1).get()));
		orderRepository.save(new OrderService(employeeRepository.findById((long) 1).get(), "Suit and Shoes 2", LocalDateTime.now().plusDays(4), customerRepository.findById(2).get()));
		orderRepository.save(new OrderService(employeeRepository.findById((long) 1).get(), "Suit and Shoes 3", LocalDateTime.now().plusDays(6), customerRepository.findById(3).get()));
	}
}
