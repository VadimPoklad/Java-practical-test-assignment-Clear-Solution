package com.vadimpoklad.clearsolution;

import static org.junit.jupiter.api.Assertions.*;

import com.vadimpoklad.clearsolution.entities.User;
import com.vadimpoklad.clearsolution.entities.UserNullable;
import com.vadimpoklad.clearsolution.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import java.util.List;



class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void getById_existingId_returnsUser() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        userRepository.create(user);

        User retrievedUser = userRepository.getById(1);

        assertEquals(user, retrievedUser);
    }

    @Test
    void getById_nonExistingId_throwsException() {
        assertThrows(EntityNotFoundException.class, () -> userRepository.getById(1));
    }

    @Test
    void getAllByDate_returnsCorrectUsers() {
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);
        User user1 = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1995, 5, 10), "123 Main St", "1234567890");
        User user2 = new User(null,"Alice", "Smith", "alice@example.com", LocalDate.of(1998, 8, 15), "456 Elm St", "0987654321");
        userRepository.create(user1);
        userRepository.create(user2);

        List<User> usersInRange = userRepository.getAllByDate(fromDate, toDate);

        assertEquals(2, usersInRange.size());
        assertTrue(usersInRange.contains(user1));
        assertTrue(usersInRange.contains(user2));
    }

    @Test
    void create_addsUser() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");

        userRepository.create(user);

        assertEquals(user, userRepository.getById(1));
    }

    @Test
    void delete_removesUser() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        userRepository.create(user);

        userRepository.delete(1);

        assertThrows(EntityNotFoundException.class, () -> userRepository.getById(1));
    }

    @Test
    void update_existingId_updatesUser() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        userRepository.create(user);
        User updatedUser = new User(null,"Alice", "Smith", "alice@example.com", LocalDate.of(1995, 5, 10), "456 Elm St", "0987654321");

        userRepository.update(1, updatedUser);

        assertEquals(updatedUser, userRepository.getById(1));
    }

    @Test
    void update_nonExistingId_throwsException() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertThrows(EntityNotFoundException.class, () -> userRepository.update(1, user));
    }

    @Test
    void patch_existingId_updatesUser() {
        User user = new User(null,"John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        userRepository.create(user);
        UserNullable userNullable = new UserNullable("Alice", null, null, LocalDate.of(1995, 5, 10), "456 Elm St", "0987654321");

        userRepository.patch(1, userNullable);

        assertEquals(userNullable.getFirstName(), userRepository.getById(1).getFirstName());
    }

    @Test
    void patch_nonExistingId_throwsException() {
        UserNullable userNullable = new UserNullable("Alice", null, null, LocalDate.of(1995, 5, 10), "456 Elm St", "0987654321");
        assertThrows(EntityNotFoundException.class, () -> userRepository.patch(1, userNullable));
    }
}
