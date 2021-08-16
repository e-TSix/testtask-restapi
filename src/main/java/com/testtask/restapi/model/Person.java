package com.testtask.restapi.model;

import com.testtask.restapi.entity.PersonEntity;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private Long id;
    private String name;
    private List<Contact> contacts;
    private List<Address> addresses;

    public static Person toModel(PersonEntity personEntity) {
        Person model = new Person();
        model.setId(personEntity.getId());
        model.setName(personEntity.getName());
        model.setContacts(personEntity.getContacts().stream().map(Contact::toModel).collect(Collectors.toList()));
        model.setAddresses(personEntity.getAddresses().stream().map(Address::toModel).collect(Collectors.toList()));
        return model;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
