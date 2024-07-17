package com.CleanItAg.CleanItAgDemoProject.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public List<Customer> findByName(String name);
}
