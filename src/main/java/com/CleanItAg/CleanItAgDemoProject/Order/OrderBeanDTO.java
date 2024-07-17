package com.CleanItAg.CleanItAgDemoProject.Order;

import java.time.LocalDateTime;

import com.CleanItAg.CleanItAgDemoProject.Customer.Customer;
import com.CleanItAg.CleanItAgDemoProject.Order.Status.statusState;

public interface OrderBeanDTO {
	public long getId();
	public LocalDateTime getCreationdate();
	public LocalDateTime getDuedate();
	public statusState getCurrentStatus();
	public Customer getCustomer();
	public String getDescription();
}
