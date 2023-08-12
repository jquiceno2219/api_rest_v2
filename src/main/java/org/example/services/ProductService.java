package org.example.services;

import org.example.domain.models.Product;
import org.example.mapping.dtos.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductDTO> listByPrice();  //1
    List<ProductDTO> filterByBaby(); //2
    List<ProductDTO> toysTenDiscountFilter(); //3
    List<ProductDTO> getCheapestOfBooks(); //5
    Map<String, ProductDTO> mostExpensiveProductByCategory(); //10

    String listByPriceDBQuery();

    void sleepThread(int millis);
}
