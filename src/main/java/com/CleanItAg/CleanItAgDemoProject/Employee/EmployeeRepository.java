package com.CleanItAg.CleanItAgDemoProject.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByName(String name);

	public Optional<Employee> findByEmail(String email);
}
