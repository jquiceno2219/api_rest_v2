package org.example.repository.impl;

import org.example.domain.enums.CategoryType;
import org.example.domain.models.Product;
import org.example.mapping.dtos.ProductDTO;
import org.example.mapping.mappers.ProductMapper;
import org.example.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;
    private List<Product> productsOrder1;
    private List<Product> productsOrder2;


    public ProductRepositoryImpl() {
        products = new ArrayList<>();

        Product product1 = new Product(1, "Libro 1", CategoryType.BOOKS, 1100.00);
        Product product2 = new Product(2, "Max Steel", CategoryType.TOYS, 20000.00);
        Product product3 = new Product(3, "Libro 2", CategoryType.BOOKS, 17000.00);
        Product product4 = new Product(4, "Baby 1", CategoryType.BABY, 22000.00);
        Product product5 = new Product(5, "La iliada", CategoryType.BOOKS, 32000.00);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.toString();

        List<Product> productsOrder1 = new ArrayList<Product>();
        productsOrder1.add(products.get(1));
        productsOrder1.add(products.get(2));
        productsOrder1.add(products.get(3));

        List<Product> productsOrder2 = new ArrayList<Product>();
        productsOrder2.add(products.get(2));
        productsOrder2.add(products.get(3));
        productsOrder2.add(products.get(4));

    }

    public List<Product> getProductsOrder1() {
        return productsOrder1;
    }

    public List<Product> getProductsOrder2() {
        return productsOrder2;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return ProductMapper.mapFrom(products);
    }

    public List<Product> getProducts() {
        return products;
    }
}