package com.testtask.restapi.repository;

import com.testtask.restapi.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    PersonEntity findByName(String name);
}
