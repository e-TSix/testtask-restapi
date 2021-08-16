package com.testtask.restapi.controller;

import com.testtask.restapi.entity.ContactEntity;
import com.testtask.restapi.exception.ContactNotFoundException;
import com.testtask.restapi.exception.TelephoneAlreadyInUseException;
import com.testtask.restapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    //  Запрос данных из таблицы person по полю ID
    @GetMapping("/{id}")
    public ResponseEntity getPerson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contactService.getContact(id));
        } catch (ContactNotFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }


    //  Создание записи в таблице contacts
    @PostMapping
    public ResponseEntity createContact(@RequestBody ContactEntity contact,
                                        @RequestParam Long personId) {
        try {
            return ResponseEntity.ok(contactService.createContact(contact, personId));
        } catch (TelephoneAlreadyInUseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Обновление записи в таблице contacts
    @PutMapping("/{id}")
    public ResponseEntity updateContact(@RequestBody ContactEntity contactUpdatedData,
                                       @PathVariable Long id) {
        try {
            return ResponseEntity.ok(contactService.updateContact(contactUpdatedData, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Удаление записи в таблице contacts
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContact(@PathVariable Long id) {
        try {
            contactService.deleteContact(id);
            return ResponseEntity.ok("Контакт удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
}
