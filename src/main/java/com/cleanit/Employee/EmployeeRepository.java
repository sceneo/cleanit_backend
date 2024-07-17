package com.cleanit.Employee;

import java.util.List;
import java.util.Optional;

import com.cleanit.Employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByName(String name);

	public Optional<Employee> findByEmail(String email);
}
