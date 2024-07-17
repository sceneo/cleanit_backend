package com.CleanItAg.CleanItAgDemoProject.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderBean, Integer> {
	List<OrderBean> findAllByCustomer_NameContainsAllIgnoreCase(String name);
	List<OrderBean> findAllByCustomer_EmailContainsAllIgnoreCase(String email);
	List<OrderBean> findAllByCustomer_NameContainsAndCustomer_EmailContainsAllIgnoreCase(String name,  String email);
}
