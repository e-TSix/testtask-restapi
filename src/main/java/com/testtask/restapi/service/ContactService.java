package com.testtask.restapi.service;

import com.testtask.restapi.entity.ContactEntity;
import com.testtask.restapi.entity.PersonEntity;
import com.testtask.restapi.exception.ContactNotFoundException;
import com.testtask.restapi.exception.TelephoneAlreadyInUseException;
import com.testtask.restapi.model.Contact;
import com.testtask.restapi.repository.ContactRepository;
import com.testtask.restapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository, PersonRepository personRepository) {
        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
    }

    //  Запрос данных из таблицы contacts по полю ID
    public Contact getContact(Long id) throws ContactNotFoundException {
        if (contactRepository.findById(id).get() == null) {
            throw new ContactNotFoundException("Контакт не найден");
        } else {
            return Contact.toModel(contactRepository.findById(id).get());
        }
    }

    //  Создание записи в таблице contacts
    public Contact createContact(ContactEntity contact, Long personId) throws TelephoneAlreadyInUseException {
        if (contactRepository.findByTelephone(contact.getTelephone()) != null) {
            throw  new TelephoneAlreadyInUseException("Телефон уже используется");
        } else {
            PersonEntity person = personRepository.findById(personId).get();
            contact.setPerson(person);
            return Contact.toModel(contactRepository.save(contact));
        }
    }

    //  Обновление записи в таблице contacts
    public Contact updateContact(ContactEntity contactUpdateData, Long id) {
        ContactEntity contact = contactRepository.findById(id).get();
        contact.setTelephone(contactUpdateData.getTelephone());
        return Contact.toModel(contactRepository.save(contact));
    }

    //  Удаление записи в таблице contacts
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
        return;
    }
}
