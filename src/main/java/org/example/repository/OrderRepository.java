package org.example.repository;

import org.example.mapping.dtos.OrderDTO;

import java.util.List;

public interface OrderRepository {

    List<OrderDTO> getAllOrders();
}
