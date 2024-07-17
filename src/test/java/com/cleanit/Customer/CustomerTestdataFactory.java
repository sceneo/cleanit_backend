package com.cleanit.Customer;

import com.cleanit.Customer.model.Customer;

import static com.cleanit.TestHelper.*;

public class CustomerTestdataFactory {

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setAddress(randomString(10));
        customer.setId(randomId());
        customer.setName(randomString(5));
        customer.setPhone(randomPhoneNumber(10));
        return customer;
    }
}
