package com.cleanit.Order;

import com.cleanit.Order.model.*;
import com.cleanit.Customer.model.Customer;
import com.cleanit.Employee.model.Employee;
import com.cleanit.Employee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.*;

import static com.cleanit.Customer.CustomerTestdataFactory.createCustomer;
import static com.cleanit.employee.EmployeeTestdataFactory.createEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private QueryService queryService;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private Authentication authentication;

    private Employee employee;
    private Employee manager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = createEmployee();
        manager = createEmployee();
    }

    @Test
    public void testGetOrders() {
        List<OrderService> orders = Arrays.asList(
                new OrderService(employee, "Description 1", LocalDateTime.now().plusDays(1), createCustomer()),
                new OrderService(employee, "Description 2", LocalDateTime.now().plusDays(2), createCustomer())
        );

        when(orderRepository.findAll()).thenReturn(orders);

        ResponseEntity<List<OrderBasicDto>> response = orderController.getOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders.size(), response.getBody().size());
    }

    @Test
    public void testGetOrdersFullDetails() {
        List<OrderService> orders = Arrays.asList(
                new OrderService(employee, "Description 1", LocalDateTime.now().plusDays(1), createCustomer()),
                new OrderService(employee, "Description 2", LocalDateTime.now().plusDays(2), createCustomer())
        );

        when(orderRepository.findAll()).thenReturn(orders);

        ResponseEntity<List<OrderFullDto>> response = orderController.getOrdersFullDetails();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders.size(), response.getBody().size());
    }

    @Test
    public void testGetOrdersById_Found() {
        int orderId = 1;
        OrderService order = new OrderService(employee, "Description", LocalDateTime.now().plusDays(1), createCustomer());

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<OrderBasicDto> response = orderController.getOrdersById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order.getId(), response.getBody().getId());
    }

    @Test
    public void testGetOrdersById_NotFound() {
        int orderId = 1;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        ResponseEntity<OrderBasicDto> response = orderController.getOrdersById(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetOrdersByQuery() {
        SearchQuery searchQuery = new SearchQuery("Customer Name e:customer@example.com");
        Dictionary<QueryService.queryKeys, Optional<String>> queryDict = new Hashtable<>();
        queryDict.put(QueryService.queryKeys.customerName, Optional.of("Customer Name"));
        queryDict.put(QueryService.queryKeys.customerEmail, Optional.of("customer@example.com"));

        List<OrderService> orders = Arrays.asList(
                new OrderService(employee, "Description 1", LocalDateTime.now().plusDays(1), createCustomer())
        );

        when(queryService.parse(searchQuery)).thenReturn(queryDict);
        when(orderRepository.findAllByCustomer_NameContainsAndCustomer_EmailContainsAllIgnoreCase("Customer Name", "customer@example.com")).thenReturn(orders);

        ResponseEntity<List<OrderService>> response = orderController.getOrdersByQuery(searchQuery);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders.size(), response.getBody().size());
    }
}
