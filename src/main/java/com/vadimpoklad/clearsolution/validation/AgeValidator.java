package com.vadimpoklad.clearsolution.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeValidator implements ConstraintValidator<AgeValidation, LocalDate> {

    @Value("${app.prop.users.minAge}")
    private int minAge;

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
        if (birthday == null) return true;
        int age = Period.between(birthday, LocalDate.now()).getYears();
        return age > minAge;
    }
}