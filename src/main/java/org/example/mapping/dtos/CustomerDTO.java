package org.example.mapping.dtos;

import org.example.domain.enums.CustomerTier;

public record CustomerDTO(long id,
        String name,
        CustomerTier tier) {
}