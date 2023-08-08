package org.example.mapping.dtos;

import org.example.domain.enums.CategoryType;

public record ProductDTO(long id,
        String name,
        CategoryType category,
        double price) {
}