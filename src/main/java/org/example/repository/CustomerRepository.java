package org.example.repository;

import org.example.mapping.dtos.CustomerDTO;

import java.util.List;

public interface CustomerRepository {

    List<CustomerDTO> getAllCustomers();
}


//getAllOrders() {
// return OrderMapper.mapFrom(orders);