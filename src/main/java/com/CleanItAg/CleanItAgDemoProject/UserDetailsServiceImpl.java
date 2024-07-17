package com.CleanItAg.CleanItAgDemoProject;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CleanItAg.CleanItAgDemoProject.Employee.Employee;
import com.CleanItAg.CleanItAgDemoProject.Employee.EmployeeRepository;

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
		
		
		//UserDetails user = User.withUsername(employee.get().getEmail()).password(employee.get().getPassword()).authorities("USER").build();
        return employee.get();
 
	}

}
