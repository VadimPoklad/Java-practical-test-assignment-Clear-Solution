package com.vadimpoklad.clearsolution.entities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserNullableTest {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidAge() {
        UserNullable user = new UserNullable(null, null, null, LocalDate.of(1990, 5, 15), null, null);
        assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    public void testInvalidAge() {
        UserNullable user = new UserNullable(null, null, null, LocalDate.now().plusDays(1), null, null);
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        UserNullable user = new UserNullable(null, null, "johnexample.com", null, null, null);
        assertFalse(validator.validate(user).isEmpty());
    }
}