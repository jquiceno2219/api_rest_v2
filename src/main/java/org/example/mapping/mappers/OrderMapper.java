package org.example.mapping.mappers;

import org.example.domain.models.Order;
import org.example.domain.models.Product;
import org.example.mapping.dtos.OrderDTO;
import org.example.mapping.dtos.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO mapFrom(Order source){
        return new OrderDTO(source.getId(),
                source.getStatus(),
                source.getOrderDate(),
                source.getDeliveryDate(),
                source.getProducts(),
                source.getCustomer());
    }

    public static Order mapFrom(OrderDTO source){
        return new Order(source.id(),
                source.status(),
                source.orderDate(),
                source.deliveryDate(),
                source.product(),
                source.customer());
    }

    public static List<OrderDTO> mapFrom(List<Order> source) {
        return source.parallelStream()
                .map(OrderMapper::mapFrom)
                .collect(Collectors.toList());
    }
}
