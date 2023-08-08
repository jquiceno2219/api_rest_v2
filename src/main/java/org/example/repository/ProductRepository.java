package org.example.repository;

import org.example.mapping.dtos.ProductDTO;

import java.util.List;

public interface ProductRepository {

    List<ProductDTO> getAllProducts();
}
