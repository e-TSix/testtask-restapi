package com.testtask.restapi.repository;

import com.testtask.restapi.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<ContactEntity, Long> {
    ContactEntity findByTelephone(String telephone);
}
