package com.testtask.restapi.controller;

import com.testtask.restapi.entity.PersonEntity;
import com.testtask.restapi.exception.PersonNotFoundException;
import com.testtask.restapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //  Запрос данных из таблицы person по полю ID
    @GetMapping("/{id}")
    public ResponseEntity getPerson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (PersonNotFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Запрос данных из таблицы person по полю NAME
    @GetMapping("/name")
    public ResponseEntity getPersonByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(personService.getPersonByName(name));
        } catch (PersonNotFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Запрос всех данных из таблицы person
    @GetMapping
    public List<PersonEntity> getAllPersons() {
        try {
            return personService.getAllPersons();
        } catch (Exception e) {
            return (List<PersonEntity>) ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Создание записи в таблице person
    @PostMapping
    public ResponseEntity createPerson(@RequestBody PersonEntity person) {
        try {
            personService.createPerson(person);
            return ResponseEntity.ok("Пользователь успешно сохранен!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Обновление записи в таблице person
    @PutMapping("/{id}")
    public ResponseEntity updatePerson(@RequestBody PersonEntity personUpdatedData,
                                     @PathVariable Long id) {
        try {
            return ResponseEntity.ok(personService.updatePerson(personUpdatedData, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Удаление записи в таблице person
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.ok("Пользователь удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
}
