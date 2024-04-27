package com.vadimpoklad.clearsolution.exceptions;

public class EntityNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Entity not found";
    }
}
