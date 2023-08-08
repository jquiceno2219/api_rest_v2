package org.example.mapping.mappers;

import org.example.domain.models.Product;
import org.example.mapping.dtos.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO mapFrom(Product source){
        return new ProductDTO(source.getId(),
                source.getName(),
                source.getCategory(),
                source.getPrice());
    }

    public static Product mapFrom(ProductDTO source){
        return new Product(source.id(),
                source.name(),
                source.category(),
                source.price());
    }

    public static List<ProductDTO> mapFrom(List<Product> source) {
        return source.parallelStream()
                .map(ProductMapper::mapFrom)
                .collect(Collectors.toList());
    }
}
