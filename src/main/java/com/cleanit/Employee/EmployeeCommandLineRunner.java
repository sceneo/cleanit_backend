package com.cleanit.Employee;


import com.cleanit.Employee.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class EmployeeCommandLineRunner implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	private PasswordEncoder bCryptPasswordEncoder;

	public EmployeeCommandLineRunner(EmployeeRepository employeeRepository, PasswordEncoder bCryptPasswordEncoder) {
		super();
		this.employeeRepository = employeeRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public void run(String... args) throws Exception{
		employeeRepository.save(new Employee(1, "Employeename1", "employee1@mail.com", "employee1 street nr city code", "+41 123 456 789", Employee.Role.employee, bCryptPasswordEncoder.encode("123")));
		employeeRepository.save(new Employee(2, "Employeename2", "employee2@mail.com", "employee2 street nr city code", "+42 123 456 789", Employee.Role.manager, bCryptPasswordEncoder.encode("123")));
	}
}
