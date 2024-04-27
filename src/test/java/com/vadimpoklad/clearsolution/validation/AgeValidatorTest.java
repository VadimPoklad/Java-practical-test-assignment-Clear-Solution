package com.vadimpoklad.clearsolution.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"app.prop.users.minAge=18"})
public class AgeValidatorTest {

    @Autowired
    private AgeValidator ageValidator;

    @Test
    public void testValidAge() {
        LocalDate validBirthday = LocalDate.of(2000, 1, 1);
        assertTrue(ageValidator.isValid(validBirthday, null));
    }

    @Test
    public void testNullBirthdayIsValid() {
        assertTrue(ageValidator.isValid(null, null));
    }

    @Test
    public void testInvalidAge() {
        LocalDate invalidBirthday = LocalDate.of(2010, 1, 1);
        assertFalse(ageValidator.isValid(invalidBirthday, null));
    }
}