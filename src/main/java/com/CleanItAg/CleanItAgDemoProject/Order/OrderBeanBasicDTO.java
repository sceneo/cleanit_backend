package com.CleanItAg.CleanItAgDemoProject.Order;


import java.time.LocalDateTime;

import org.springframework.security.access.prepost.PreAuthorize;

import com.CleanItAg.CleanItAgDemoProject.Customer.Customer;
import com.CleanItAg.CleanItAgDemoProject.Order.Status.statusState;


public class OrderBeanBasicDTO implements OrderBeanDTO {

	
	private long id;
	private LocalDateTime creationdate;
	private LocalDateTime duedate;
	private statusState currentStatus;
	
	private Customer customer;
	private String description;

	public OrderBeanBasicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderBeanBasicDTO(OrderBean order) {
		super();
		this.id = order.getId();
		this.creationdate = order.getCreationdate();
		this.duedate = order.getDuedate();
		this.currentStatus = order.getCurrentStatus();
		this.customer = order.getCustomer();
		this.description = order.getDescription();
	}


	@Override
	public long getId() {
		return id;
	}

	@Override
	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	@Override
	public LocalDateTime getDuedate() {
		return duedate;
	}

	@Override
	public statusState getCurrentStatus() {
		return currentStatus;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
}
