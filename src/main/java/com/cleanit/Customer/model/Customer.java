package com.cleanit.Customer.model;

import com.cleanit.Person.Person;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Person {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String name, String email, String address, String phone) {
		super(id, name, email, address, phone);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phone="
				+ phone + "]";
	}

}
