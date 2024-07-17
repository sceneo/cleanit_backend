package com.CleanItAg.CleanItAgDemoProject.Employee.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.CleanItAg.CleanItAgDemoProject.Person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee extends Person implements UserDetails {

	public enum Role{employee,manager};
	
    private Role role;
	
	private String password;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(int id, String name, String email, String address, String phone, Role role, String password) {
		super(id, name, email, address, phone);
		this.role = role;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phone="
				+ phone + "]";
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		    List<GrantedAuthority> authorities = new ArrayList<>();
		  
		    authorities.add(new SimpleGrantedAuthority(role.toString()));
		       
		    
		    return authorities;
	}


	@JsonIgnore
	@Override
	public String getPassword() {
		return this.password;
	}


	@JsonIgnore
	@Override
	public String getUsername() {
		// This is used for Principal.getName(). The Login "name" is defined via the implementation of UserDetailsService.
		return Long.toString(this.getId());
	}


	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
