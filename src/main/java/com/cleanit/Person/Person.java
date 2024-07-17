package com.cleanit.Person;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

	@Id
	@GeneratedValue
	protected long id;
	protected String name;
	protected String email;
	protected String address;
	protected String phone;

	public Person() {
		super();
	}

	public Person(int id, String name, String email, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person [id="
				+ id
				+ ", name="
				+ name
				+ ", email="
				+ email
				+ ", address="
				+ address
				+ ", phone="
				+ phone
				+ "]";
	}

}
