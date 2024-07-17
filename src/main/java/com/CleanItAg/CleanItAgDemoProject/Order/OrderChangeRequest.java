package com.CleanItAg.CleanItAgDemoProject.Order;

import com.CleanItAg.CleanItAgDemoProject.Order.Status.statusState;

public class OrderChangeRequest {
	private statusState newStatus;

	
	public OrderChangeRequest(statusState newStatus, int issuerId) {
		super();
		this.newStatus = newStatus;
	
	}
	
	public statusState getNewStatus() {
		return newStatus;
	}
	
}
