package com.cleanit.Order.model;

import java.time.LocalDateTime;

import com.cleanit.Customer.model.Customer;
import com.cleanit.Order.model.Status.statusState;

public interface OrderDto {
	public long getId();
	public LocalDateTime getCreationdate();
	public LocalDateTime getDuedate();
	public statusState getCurrentStatus();
	public Customer getCustomer();
	public String getDescription();
}
