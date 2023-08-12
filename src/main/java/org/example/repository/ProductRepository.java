package org.example.repository;

import org.example.domain.models.Product;
import org.example.mapping.dtos.ProductDTO;

import java.util.List;

public interface ProductRepository {

    List<Product> getProductsOrder1();
    List<Product> getProductsOrder2();

    List<ProductDTO> getAllProducts();
}
