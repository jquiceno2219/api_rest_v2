package org.example.mapping.mappers;

import org.example.domain.models.Customer;
import org.example.mapping.dtos.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDTO mapFrom(Customer source){
        return new CustomerDTO(source.getId(),
                source.getName(),
                source.getTier());
    }

    public static Customer mapFrom(CustomerDTO source){
        return new Customer(source.id(),
                source.name(),
                source.tier());
    }

    public static List<CustomerDTO> mapFrom(List<Customer> source) {
        return source.parallelStream()
                .map(CustomerMapper::mapFrom)
                .collect(Collectors.toList());
    }

    public static List<Customer> mapFromDto(List<CustomerDTO> source) {
        return source.parallelStream().map(CustomerMapper::mapFrom).collect(Collectors.toList());
    }
}
