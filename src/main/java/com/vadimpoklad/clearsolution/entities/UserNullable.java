package com.vadimpoklad.clearsolution.entities;

import com.vadimpoklad.clearsolution.validation.AgeValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserNullable {
    public UserNullable(String firstName, String lastName, String email, LocalDate birthday, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Past(message = "Birthday must be in the past")
    @AgeValidation
    private LocalDate birthday;
    private String address;
    private String phoneNumber;

    @Override
    public String toString() {
        return "{" +
                "\"firstName\": \"" + firstName + "\"," +
                "\"lastName\": \"" + lastName + "\"," +
                "\"email\": \"" + email + "\"," +
                "\"birthday\": \"" + birthday + "\"," +
                "\"address\": \"" + address + "\"," +
                "\"phoneNumber\": \"" + phoneNumber + "\"" +
                "}";
    }
}
