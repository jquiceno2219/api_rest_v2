package org.example.mapping.dtos;

import org.example.domain.enums.OrderStatus;
import org.example.domain.models.Customer;
import org.example.domain.models.Product;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(long id,
        OrderStatus status,
        LocalDate orderDate,
        LocalDate deliveryDate,
        List<Product>product,
        Customer customer) {
}
