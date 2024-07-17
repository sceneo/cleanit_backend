package com.cleanit.UserDetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cleanit.Employee.model.Employee;
import com.cleanit.Employee.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Employee> employee = employeeRepository.findByEmail(email);
		if (employee.isEmpty()) {
			throw new UsernameNotFoundException("user with email" + email
                + " not found");
			}

        return employee.get();
	}

}
