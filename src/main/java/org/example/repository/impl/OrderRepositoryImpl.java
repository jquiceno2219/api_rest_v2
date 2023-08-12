package org.example.repository.impl;

import org.example.domain.enums.OrderStatus;
import org.example.domain.models.Customer;
import org.example.domain.models.Order;
import org.example.domain.models.Product;
import org.example.mapping.dtos.CustomerDTO;
import org.example.mapping.dtos.OrderDTO;
import org.example.mapping.mappers.CustomerMapper;
import org.example.mapping.mappers.OrderMapper;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private List<Order> orders;

    public OrderRepositoryImpl(ProductRepository productRepository, CustomerRepositoryImpl customerRepository) {
        orders = new ArrayList<>();

        List<Product> productsOrder1 = productRepository.getProductsOrder1();
        List<Product> productsOrder2 = productRepository.getProductsOrder2();

        List<Customer> customers = CustomerMapper.mapFromDto(customerRepository.getAllCustomers());

        Order order1 = new Order(1L,
                OrderStatus.Delivered,
                LocalDate.of(2023, 02, 20),
                LocalDate.of(2023, 03, 24),
                productsOrder1,
                customers.get(0));
        Order order2 = new Order(2L,
                OrderStatus.Pending,
                LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 14),
                productsOrder1,
                customers.get(1));
        Order order3 = new Order(3L,
                OrderStatus.Pending,
                LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 14),
                productsOrder2,
                customers.get(1));
        Order order4 = new Order(4L,
                OrderStatus.Pending,
                LocalDate.of(2023, 03, 7),
                LocalDate.of(2023, 04, 14),
                productsOrder2,
                customers.get(0));


        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return OrderMapper.mapFrom(orders);
    }
}
