package org.example.repository.impl;

import org.example.domain.enums.CustomerTier;
import org.example.domain.models.Customer;
import org.example.mapping.dtos.CustomerDTO;
import org.example.mapping.mappers.CustomerMapper;
import org.example.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private List<Customer> customers;
    public CustomerRepositoryImpl() {
        customers = new ArrayList<>();
        Customer customer1 = new Customer(1, "Juan", CustomerTier.T1);
        Customer customer2 = new Customer(2, "Pepita", CustomerTier.T2);

        customers.add(customer1);
        customers.add(customer2);
        customers.toString();

        //tambien customers = List.of(customer1, customer2);

    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return CustomerMapper.mapFrom(customers);
    }
}
