package com.CleanItAg.CleanItAgDemoProject.Order.model;

import com.CleanItAg.CleanItAgDemoProject.Order.model.Status.statusState;

public class OrderChangeRequest {
	private final statusState newStatus;

	
	public OrderChangeRequest(statusState newStatus, int issuerId) {
		super();
		this.newStatus = newStatus;
	
	}
	
	public statusState getNewStatus() {
		return newStatus;
	}
	
}
