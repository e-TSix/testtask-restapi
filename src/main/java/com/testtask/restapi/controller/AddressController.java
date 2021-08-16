package com.testtask.restapi.controller;

import com.testtask.restapi.entity.AddressEntity;
import com.testtask.restapi.exception.AddressNotFoundException;
import com.testtask.restapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    //  Запрос данных из таблицы address по полю ID
    @GetMapping("/{id}")
    public ResponseEntity getAddress(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(addressService.getAddress(id));
        } catch (AddressNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Создание записи в таблице address
    @PostMapping
    public ResponseEntity createAddress(@RequestBody AddressEntity address,
                                        @RequestParam Long personId) {
        try {
            return ResponseEntity.ok(addressService.createAddress(address, personId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Обновление записи в таблице address
    @PutMapping("/{id}")
    public ResponseEntity updateAddress(@RequestBody AddressEntity addressUpdatedData,
                                        @PathVariable Long id) {
        try {
            return ResponseEntity.ok(addressService.updateAddress(addressUpdatedData, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    //  Удаление записи в таблице фввкуыы
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.ok("Адрес удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
}
