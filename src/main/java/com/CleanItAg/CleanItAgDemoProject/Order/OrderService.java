package com.CleanItAg.CleanItAgDemoProject.Order;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.CleanItAg.CleanItAgDemoProject.Customer.model.Customer;
import com.CleanItAg.CleanItAgDemoProject.Employee.model.Employee;
import com.CleanItAg.CleanItAgDemoProject.Order.model.Status;
import com.CleanItAg.CleanItAgDemoProject.Order.model.Status.statusState;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderService {

	@Id
	@GeneratedValue
	private long id;
	private LocalDateTime creationdate;
	private LocalDateTime duedate;
	private statusState currentStatus;
	
	@ElementCollection
	private List<Status> statusHistory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	private String description;

	
	public OrderService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderService(Employee issuer, String description, LocalDateTime duedate, Customer customer) {
		super();
		this.creationdate = LocalDateTime.now();
		this.duedate = duedate;
		this.statusHistory = new ArrayList<Status>(List.of(new Status(issuer, statusState.initial, LocalDateTime.now())));
		this.currentStatus=statusState.being_cleaned;
		this.customer = customer;
		this.description = description;
	}
	
	public void changeStatus(statusState newStatus, Employee issuer) {
		statusHistory.add(new Status(issuer, newStatus, LocalDateTime.now()));
		currentStatus = newStatus;
	}
	

	public statusState getCurrentStatus() {
		return currentStatus;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public LocalDateTime getDuedate() {
		return duedate;
	}

	public List<Status> getStatusHistory() {
		return statusHistory;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getDescription() {
		return description;
	}

	

	
	
}
