package com.vadimpoklad.clearsolution.entities;

import com.vadimpoklad.clearsolution.validation.AgeValidation;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    public User(Integer id, String firstName, String lastName, String email, LocalDate birthday, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @Past(message = "Birthday must be in the past")
    @AgeValidation
    private LocalDate birthday;
    private String address;
    private String phoneNumber;

    public void updateFromNullable(UserNullable nullableUser) {
        if (nullableUser.getFirstName() != null) {
            this.firstName = nullableUser.getFirstName();
        }
        if (nullableUser.getLastName() != null) {
            this.lastName = nullableUser.getLastName();
        }
        if (nullableUser.getEmail() != null) {
            this.email = nullableUser.getEmail();
        }
        if (nullableUser.getBirthday() != null) {
            this.birthday = nullableUser.getBirthday();
        }
        if (nullableUser.getAddress() != null) {
            this.address = nullableUser.getAddress();
        }
        if (nullableUser.getPhoneNumber() != null) {
            this.phoneNumber = nullableUser.getPhoneNumber();
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"," +
                "\"firstName\": \"" + firstName + "\"," +
                "\"lastName\": \"" + lastName + "\"," +
                "\"email\": \"" + email + "\"," +
                "\"birthday\": \"" + birthday + "\"," +
                "\"address\": \"" + address + "\"," +
                "\"phoneNumber\": \"" + phoneNumber + "\"" +
                "}";
    }
}
