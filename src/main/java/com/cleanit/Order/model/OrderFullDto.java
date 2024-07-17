package com.cleanit.Order.model;


import java.time.LocalDateTime;
import java.util.List;

import com.cleanit.Customer.model.Customer;
import com.cleanit.Order.OrderService;


public class OrderFullDto implements OrderDto {

	
	private long id;
	private LocalDateTime creationdate;
	private LocalDateTime duedate;
	private Status.statusState currentStatus;
	
	private Customer customer;
	private String description;
	
	private List<Status> statusHistory;

	
	public OrderFullDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderFullDto(OrderService order) {
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
	public Status.statusState getCurrentStatus() {
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
