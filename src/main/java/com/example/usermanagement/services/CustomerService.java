package com.example.usermanagement.services;

import com.example.usermanagement.models.Customer;
import com.example.usermanagement.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for customer management.
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Adds a new customer to the database.
     * @param customer The customer entity to be added.
     * @return The saved customer entity.
     */
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieves all customers from the database.
     * @return List of customers.
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a specific customer by ID.
     * @param id The customer ID.
     * @return The customer entity.
     * @throws IllegalArgumentException if the customer is not found.
     */
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }
}