package com.cleanit.employee;

import com.cleanit.Employee.model.Employee;

import static com.cleanit.TestHelper.*;

public class EmployeeTestdataFactory {
    public static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setAddress(randomString(10));
        employee.setId(randomId());
        employee.setName(randomString(5));
        employee.setPhone(randomPhoneNumber(10));
        return employee;
    }
}
