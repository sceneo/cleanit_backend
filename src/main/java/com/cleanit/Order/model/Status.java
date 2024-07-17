package com.cleanit.Order.model;

import java.time.LocalDateTime;

import com.cleanit.Employee.model.Employee;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Status {
	public enum statusState {
		initial, being_cleaned, ready_for_pickup, finished
	};

	private statusState status;
	private LocalDateTime dateIssued;
	
	@ManyToOne
	private Employee issuer;

	public Status() {
		super();
	}

	public Status(Employee issuer, statusState status, LocalDateTime dateIssued) {
		super();
		this.status = status;
		this.dateIssued = dateIssued;
		this.issuer = issuer;
	}

	public statusState getStatus() {
		return status;
	}

	public LocalDateTime getDateIssued() {
		return dateIssued;
	}

	public Employee getIssuer() {
		return issuer;
	}
	

}
