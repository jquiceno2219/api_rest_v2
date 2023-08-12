package org.example.services.impl;

import org.example.domain.enums.CategoryType;
import org.example.mapping.dtos.ProductDTO;
import org.example.repository.ProductRepository;
import org.example.services.ProductService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;
    private List<ProductDTO> products = new ArrayList<>();
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
        products = repository.getAllProducts();
    }



    //METODO UNO
    public List<ProductDTO> listByPrice() {
        String category = "Libros";
        List<ProductDTO> filteredProducts = products.stream()
                .filter(e -> e.category().equals(CategoryType.fromValue(category)))
                .filter(e -> e.price() > 100)
                .toList();

        return filteredProducts;
    }


    //MÉTODO DOS
    public List<ProductDTO> filterByBaby() {
        String category = "Bebé";
        List<ProductDTO> filteredByBaby = products.stream()
                .filter(e ->
                        e.category()
                                .equals(CategoryType.fromValue(category)))
                .toList();
        filteredByBaby.forEach(System.out::println);

        return filteredByBaby;

    }

    //MÉTODO TRES
    public List<ProductDTO> toysTenDiscountFilter() {
        String category = "Juguetes";
        List<ProductDTO> filteredByToys = products.stream()
                .filter(e -> e.category().equals(CategoryType.fromValue(category)))
                .map(e -> {
                    CompletableFuture<Double> discountedPriceFuture = applyToyDiscountAsync(e.price());
                    return discountedPriceFuture
                    .thenApply(discountedPrice -> new ProductDTO(e.id(), e.name(), e.category(), discountedPrice));})
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        filteredByToys.forEach(System.out::println);
        return filteredByToys;
    }

    private CompletableFuture<Double> applyToyDiscountAsync(double price) {
        return CompletableFuture.supplyAsync(() -> {
            double discount = 0.1;
            return applyToyDiscount(price, discount);
        });
    }

    private static double applyToyDiscount(double price, double discount) {
        return price - (price * discount);
    }

    //MÉTODO CINCO
    public List<ProductDTO> getCheapestOfBooks() { //punto 5
        List<ProductDTO> cheapestOfBooks = products.stream()
                .filter(e -> e.category().equals(CategoryType.BOOKS))
                .sorted(Comparator.comparing(ProductDTO::price))
                .limit(2)
                .collect(Collectors.toList());
        cheapestOfBooks.forEach(System.out::println);

        return cheapestOfBooks;
    }


    //DIEZ
    public Map<String, ProductDTO> mostExpensiveProductByCategory (){
        Map<String, ProductDTO> mostExpensiveProd = new HashMap<>();
        for (ProductDTO product : products) {
            mostExpensiveProd.merge(String.valueOf(product.category()), product,
                    (e, r) -> e.price() > r.price() ? e : r);
        }
        mostExpensiveProd.values().forEach(System.out::println);
        return mostExpensiveProd;
    }

    @Override
    public String listByPriceDBQuery() {
        System.out.println("Please wait while we search.");
        sleepThread(2000);

        List<ProductDTO> filteredProducts = listByPrice();
        filteredProducts.forEach(System.out::println);
        return "Thread ended ";
    }

    @Override
    public void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
