package org.example.services.impl;

import org.example.domain.enums.CustomerTier;
import org.example.domain.models.Customer;
import org.example.domain.models.Product;
import org.example.mapping.dtos.OrderDTO;
import org.example.repository.OrderRepository;
import org.example.services.OrderService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;
    private List<OrderDTO> orders = new ArrayList<>();
    public OrderServiceImpl(OrderRepository repository){
        this.repository = repository;
        orders = repository.getAllOrders();
    }

    //MÉTODO CUATROOOO nombres para método cuatro :> nideabro
    public List<OrderDTO> filterByOrderAndTier() {
        List<OrderDTO> filteredOrders = orders.stream()
                .filter(e -> e.customer().getTier().equals(CustomerTier.T2))
                .filter(e -> !e.orderDate().isBefore(LocalDate.of(2023, 1, 1)))
                .filter(e -> !e.orderDate().isAfter(LocalDate.of(2023, 4, 1)))
                .distinct()
                .collect(Collectors.toList());

        filteredOrders.forEach(System.out::println);

        return filteredOrders;
    }

    public List<OrderDTO> listThreeMostRecentOrders() {
        List<OrderDTO> ThreeMostRecentOrders = orders.stream()
                .sorted(Comparator.comparing(OrderDTO::orderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
        return ThreeMostRecentOrders;
    }

    //SIETE
    public double totalOrderPrice() {
        double totalOrderPrice = orders.stream()
                .filter(e -> e.orderDate().compareTo(LocalDate.of(2023, 01, 02)) >= 0)
                .filter(e -> e.orderDate().compareTo(LocalDate.of(2023, 05, 02)) < 0)
                .flatMap(e -> e.product().stream())
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Suma total: " + totalOrderPrice);

        return totalOrderPrice;
    }


    //OCHO
    public double averagePriceOrder() {

        return orders.stream()
                .filter(e -> e.orderDate().equals(LocalDate.of(2023, 01, 10)))
                .flatMap(e -> e.product().stream())
                .mapToDouble(Product::getPrice)
                .average().getAsDouble();
    }


    //NUEVE
    public Map<Customer, List<OrderDTO>> ordersGroupedByCustomer() {
        Map<Customer, List<OrderDTO>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(OrderDTO::customer));

        return ordersByCustomer;
    }

    @Override
    public String listThreeDBQuery() {
        System.out.println("Please wait while we search.");
        sleepThread(2000);

        List<OrderDTO> ThreeMostRecent = listThreeMostRecentOrders();
        ThreeMostRecent.forEach(System.out::println);
        return "Thread ended.";
    }


    @Override
    public void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return repository.getAllOrders();
    }
}
