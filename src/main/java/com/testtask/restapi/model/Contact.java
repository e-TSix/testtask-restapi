package com.testtask.restapi.model;

import com.testtask.restapi.entity.ContactEntity;

public class Contact {

    private String telephone;

    public static Contact toModel(ContactEntity contactEntity) {
        Contact model = new Contact();
        model.setTelephone(contactEntity.getTelephone());
        return model;
    }

    public Contact() {
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
