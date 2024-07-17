package com.cleanit.Customer;

import com.cleanit.Customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.cleanit.Customer.CustomerTestdataFactory.createCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testGetCustomers() {
        // Arrange
        Customer customer1 = createCustomer();
        Customer customer2 = createCustomer();
        List<Customer> customerList = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        ResponseEntity<List<Customer>> response = customerController.getCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerList, response.getBody());
    }

    @Test
    public void testGetCustomersById_Found() {
        // Arrange
        int customerId = 1;
        Customer customer = createCustomer();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        ResponseEntity<Customer> response = customerController.getCustomersById(customerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testGetCustomersById_NotFound() {
        // Arrange
        int customerId = 1;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Customer> response = customerController.getCustomersById(customerId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
