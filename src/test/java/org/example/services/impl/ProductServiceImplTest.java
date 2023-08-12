package org.example.services.impl;

import org.example.domain.enums.CategoryType;
import org.example.domain.models.Product;
import org.example.mapping.dtos.ProductDTO;
import org.example.repository.ProductRepository;
import org.example.repository.impl.ProductRepositoryImpl;
import org.example.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private List<ProductDTO> products;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();


        products.add(new ProductDTO(1, "Libro 1", CategoryType.BOOKS, 1100.00));
        products.add(new ProductDTO(2, "Max Steel", CategoryType.TOYS, 20000.00));
        products.add(new ProductDTO(3, "Libro 2", CategoryType.BOOKS, 17000.00));
        products.add(new ProductDTO(4, "Baby 1", CategoryType.BABY, 22000.00));
        products.add(new ProductDTO(5, "La iliada", CategoryType.BOOKS, 32000.00));

        ProductRepository productRepository = new ProductRepositoryImpl();

        productService = new ProductServiceImpl(productRepository);
    }


    @Test
    void testListByPrice() {
        List<ProductDTO> expected = new ArrayList<>();
        expected.add(products.get(0));
        expected.add(products.get(2));
        expected.add(products.get(4));

        List<ProductDTO> result = productService.listByPrice();


        assertEquals(expected, result, "La operación se realiza de manera correcta");
    }

    @Test
    void testFilterByBaby() {
        List<ProductDTO> expected = new ArrayList<>();
        expected.add(products.get(3));

        List<ProductDTO> result = productService.filterByBaby();

        assertEquals(expected, result, "La operación se realiza de manera correcta");
    }

    @Test
    void testGetCheapestOfBooks() {
        List<ProductDTO> expected = new ArrayList<>();
        expected.add(products.get(0));
        expected.add(products.get(2));

        List<ProductDTO> result = productService.getCheapestOfBooks();

        assertEquals(expected, result, "La operación se realiza de manera correcta");
    }

}
