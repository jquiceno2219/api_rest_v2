package org.example.services;

import org.example.mapping.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
}
