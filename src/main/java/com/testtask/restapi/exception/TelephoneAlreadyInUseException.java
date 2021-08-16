package com.testtask.restapi.exception;

public class TelephoneAlreadyInUseException extends Exception {
    public TelephoneAlreadyInUseException(String message) {
        super(message);
    }
}
