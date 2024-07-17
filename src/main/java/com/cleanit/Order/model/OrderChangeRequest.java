package com.cleanit.Order.model;

public class OrderChangeRequest {
	private final Status.statusState newStatus;

	
	public OrderChangeRequest(Status.statusState newStatus, long issuerId) {
		super();
		this.newStatus = newStatus;
	
	}
	
	public Status.statusState getNewStatus() {
		return newStatus;
	}
	
}
