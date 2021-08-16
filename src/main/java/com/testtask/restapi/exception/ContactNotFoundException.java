package com.testtask.restapi.exception;

public class ContactNotFoundException extends Exception{
    public ContactNotFoundException(String message) {
        super(message);
    }
}
