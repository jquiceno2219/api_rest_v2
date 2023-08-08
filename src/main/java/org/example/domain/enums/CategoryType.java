package org.example.domain.enums;

import java.util.Arrays;

public enum CategoryType {
    BOOKS("Libros"),
    FOOD("Comida"),
    TOYS("Juguetes"),
    BABY("Bebé");

    private final String value;

    public String getValue() {
        return value;
    }
    CategoryType(String value){
        this.value = value;
    }

    public static CategoryType fromValue(String value) {
        return Arrays.stream(CategoryType.values())
                .filter(e->e.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(()->new RuntimeException("Category not found"));

    }
}


