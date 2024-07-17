package com.CleanItAg.CleanItAgDemoProject.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderService, Integer> {
	List<OrderService> findAllByCustomer_NameContainsAllIgnoreCase(String name);
	List<OrderService> findAllByCustomer_EmailContainsAllIgnoreCase(String email);
	List<OrderService> findAllByCustomer_NameContainsAndCustomer_EmailContainsAllIgnoreCase(String name, String email);
}
