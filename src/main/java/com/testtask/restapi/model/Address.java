package com.testtask.restapi.model;

import com.testtask.restapi.entity.AddressEntity;

public class Address {

    private String city;
    private String street;

    public static Address toModel(AddressEntity addressEntity) {
        Address model = new Address();
        model.setCity(addressEntity.getCity());
        model.setStreet(addressEntity.getStreet());
        return model;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
