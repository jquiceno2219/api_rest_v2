package org.example.domain.enums;

import java.util.Arrays;

public enum CustomerTier {
    T1("Tier 1"),
    T2("Tier 2"),
    T3("Tier 3");

    private final String clientValue;

    CustomerTier(String clientValue) {
        this.clientValue = clientValue;
    }

    public String getClientValue() {
        return clientValue;
    }

    public static CustomerTier fromClientValue(String clientValue) {
        return Arrays.stream(CustomerTier.values())
                .filter(e->e.getClientValue().equalsIgnoreCase(clientValue))
                .findAny()
                .orElseThrow(()->new RuntimeException("Tier not found"));
    }
}