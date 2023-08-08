package org.example.domain.models;

import org.example.domain.enums.CategoryType;

public class Product {
    private long id;
    private String name;
    private CategoryType category;
    private double price;

    public Product(long id, String name, CategoryType category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product " +
                "id=" + id +
                "\n {name='" + name + '\'' +
                "\n category=" + category +
                "\n price=" + price +
                "} \n" ;

    }

}
