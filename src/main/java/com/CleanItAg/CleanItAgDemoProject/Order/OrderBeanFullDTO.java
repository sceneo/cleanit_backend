package com.CleanItAg.CleanItAgDemoProject.Order;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.CleanItAg.CleanItAgDemoProject.Customer.Customer;
import com.CleanItAg.CleanItAgDemoProject.Order.Status.statusState;


public class OrderBeanFullDTO implements OrderBeanDTO {

	
	private long id;
	private LocalDateTime creationdate;
	private LocalDateTime duedate;
	private statusState currentStatus;
	
	private Customer customer;
	private String description;
	
	private List<Status> statusHistory;

	
	public OrderBeanFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderBeanFullDTO(OrderBean order) {
		super();
		this.id = order.getId();
		this.creationdate = order.getCreationdate();
		this.duedate = order.getDuedate();
		this.currentStatus = order.getCurrentStatus();
		this.customer = order.getCustomer();
		this.description = order.getDescription();
		this.statusHistory = order.getStatusHistory();
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

	public List<Status> getStatusHistory() {
		return statusHistory;
	}
	
	
}
