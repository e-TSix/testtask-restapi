package com.testtask.restapi.service;

import com.testtask.restapi.entity.PersonEntity;
import com.testtask.restapi.exception.PersonNotFoundException;
import com.testtask.restapi.model.Person;
import com.testtask.restapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //  Запрос данных из таблицы person по полю ID
    public Person getPerson(Long id) throws PersonNotFoundException {
        if (personRepository.findById(id).get() == null) {
            throw new PersonNotFoundException("Человек не найден!");
        } else {
            return Person.toModel(personRepository.findById(id).get());
        }
    }

    //  Запрос данных из таблицы person по полю NAME
    public Person getPersonByName(String name) throws PersonNotFoundException {
        PersonEntity person = personRepository.findByName(name);
        if (person.getName() == null) {
            throw new PersonNotFoundException("Пользователь не найден");
        }
        return Person.toModel(person);
    }

    //  Запрос всех данных из таблицы person
    public List<PersonEntity> getAllPersons() {
        return (List<PersonEntity>) personRepository.findAll();
    }

    //  Создание записи в таблице person
    public PersonEntity createPerson(PersonEntity person) {
        return personRepository.save(person);
    }

    //  Обновление записи в таблице person
    public PersonEntity updatePerson(PersonEntity personUpdateData, Long id) {
        PersonEntity person = personRepository.findById(id).get();
        person.setName(personUpdateData.getName());
        return personRepository.save(person);
    }

    //  Удаление записи в таблице person
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
        return;
    }
}
