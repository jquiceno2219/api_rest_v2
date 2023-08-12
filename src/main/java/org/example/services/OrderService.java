package org.example.services;

import org.example.domain.models.Customer;
import org.example.domain.models.Order;
import org.example.mapping.dtos.CustomerDTO;
import org.example.mapping.dtos.OrderDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDTO> filterByOrderAndTier();

    String listThreeDBQuery();

    void sleepThread(int millis);

    List<OrderDTO> getAllOrders();
    List<OrderDTO> listThreeMostRecentOrders();
    double totalOrderPrice();
    double averagePriceOrder();
    Map<Customer, List<OrderDTO>> ordersGroupedByCustomer();



}
