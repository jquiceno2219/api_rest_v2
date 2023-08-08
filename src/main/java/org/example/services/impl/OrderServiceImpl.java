package org.example.services.impl;

import org.example.mapping.dtos.OrderDTO;
import org.example.repository.OrderRepository;
import org.example.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;
    public OrderServiceImpl(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return repository.getAllOrders();
    }
}
