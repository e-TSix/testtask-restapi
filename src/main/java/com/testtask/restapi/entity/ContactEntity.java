package com.testtask.restapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "telephone")
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private PersonEntity personEntity;

    public ContactEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public PersonEntity getPerson() {
        return personEntity;
    }

    public void setPerson(PersonEntity person) {
        this.personEntity = person;
    }
}
