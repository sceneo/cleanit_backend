package com.CleanItAg.CleanItAgDemoProject.Order;

import java.time.LocalDateTime;

import com.CleanItAg.CleanItAgDemoProject.Employee.Employee;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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
		// TODO Auto-generated constructor stub
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
