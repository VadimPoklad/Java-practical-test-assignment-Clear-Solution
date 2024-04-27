package com.vadimpoklad.clearsolution.entities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidAge() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 5, 15), "123 Main St", "555-1234");
        assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    public void testInvalidAge() {
        User user = new User(null,"Jane", "Smith", "jane@example.com", LocalDate.now().plusDays(1), "456 Elm St", "555-5678");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testBlankFirstName() {
        User user = new User(null,"", "Doe", "john@example.com", LocalDate.of(1990, 5, 15), "123 Main St", "555-1234");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testBlankLastName() {
        User user = new User(null,"John", "", "john@example.com", LocalDate.of(1990, 5, 15), "123 Main St", "555-1234");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testBlankEmail() {
        User user = new User(null,"John", "Doe", "", LocalDate.of(1990, 5, 15), "123 Main St", "555-1234");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        User user = new User(null,"John", "Doe", "johnexample.com", LocalDate.of(1990, 5, 15), "123 Main St", "555-1234");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testNullBirthday() {
        User user = new User(null,"John", "Doe", "john@example.com", null, "123 Main St", "555-1234");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testFutureBirthday() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.now().plusDays(1), "123 Main St", "555-1234");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testUpdateFromNullable() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 5, 15), "123 Main St", "555-1234");
        UserNullable nullableUser = new UserNullable(null, null, "updated@example.com", LocalDate.of(1985, 3, 20), null, null);
        user.updateFromNullable(nullableUser);
        assertTrue(validator.validate(user).isEmpty());
        assertEquals("updated@example.com", user.getEmail());
        assertEquals(user.getBirthday(), LocalDate.of(1985, 3, 20));
    }
}