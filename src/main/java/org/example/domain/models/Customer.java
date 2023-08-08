package org.example.domain.models;


import org.example.domain.enums.CustomerTier;

public class Customer {
    private long id;
    private String name;
    private CustomerTier tier;

    public Customer(long id, String name, CustomerTier tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    public Customer() {
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

    public CustomerTier getTier() {
        return tier;
    }

    public void setTier(CustomerTier tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer " +
                "id=" + id +
                "\n {name='" + name + '\'' +
                "\n tier=" + tier +
                "} \n ";
    }
}
