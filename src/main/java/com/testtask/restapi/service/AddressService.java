package com.testtask.restapi.service;

import com.testtask.restapi.entity.AddressEntity;
import com.testtask.restapi.entity.PersonEntity;
import com.testtask.restapi.exception.AddressNotFoundException;
import com.testtask.restapi.model.Address;
import com.testtask.restapi.repository.AddressRepository;
import com.testtask.restapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    public final PersonRepository personRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    //  Запрос данных из таблицы address по полю ID
    public Address getAddress(Long id) throws AddressNotFoundException {
        if (addressRepository.findById(id).get() == null) {
            throw new AddressNotFoundException("Адрес не найден");
        } else {
            return Address.toModel(addressRepository.findById(id).get());
        }
    }

    //  Создание записи в таблице address
    public Address createAddress(AddressEntity address, Long personId) {
        PersonEntity person = personRepository.findById(personId).get();
        address.setPersonEntityAddress(person);
        return Address.toModel(addressRepository.save(address));
    }

    //  Обновление записи в таблице address
    public Address updateAddress(AddressEntity addressUpdateData, Long id) {
        AddressEntity address = addressRepository.findById(id).get();
        address.setCity(addressUpdateData.getCity());
        address.setStreet(addressUpdateData.getStreet());
        return Address.toModel(addressRepository.save(address));
    }

    //  Удаление записи в таблице address
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
        return;
    }
}
