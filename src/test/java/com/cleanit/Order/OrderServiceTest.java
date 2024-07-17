package com.cleanit.Order;

import com.cleanit.Customer.model.Customer;
import com.cleanit.Employee.model.Employee;
import com.cleanit.Order.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.cleanit.Customer.CustomerTestdataFactory.createCustomer;
import static com.cleanit.employee.EmployeeTestdataFactory.createEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    private OrderService orderService;
    private Customer customer;
    private Employee issuer;
    private LocalDateTime dueDate;
    private String description;

    @BeforeEach
    public void setUp() {
        customer = createCustomer();
        issuer = createEmployee();
        dueDate = LocalDateTime.now().plusDays(2);
        description = "Cleaning order description";

        orderService = new OrderService(issuer, description, dueDate, customer);
    }

    @Test
    public void testOrderServiceCreation() {
        assertEquals(description, orderService.getDescription());
        assertEquals(dueDate, orderService.getDuedate());
        assertEquals(customer, orderService.getCustomer());
        assertEquals(1, orderService.getStatusHistory().size());
        assertEquals(Status.statusState.being_cleaned, orderService.getCurrentStatus());
    }

    @Test
    public void testChangeStatus() {
        Employee newIssuer = createEmployee();
        Status.statusState newStatus = Status.statusState.finished;

        orderService.changeStatus(newStatus, newIssuer);

        List<Status> statusHistory = orderService.getStatusHistory();
        assertEquals(2, statusHistory.size());
        assertEquals(newStatus, orderService.getCurrentStatus());
        assertEquals(newIssuer, statusHistory.get(1).getIssuer());
    }
}
