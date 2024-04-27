package com.vadimpoklad.clearsolution;

import com.vadimpoklad.clearsolution.entities.User;
import com.vadimpoklad.clearsolution.entities.UserNullable;
import com.vadimpoklad.clearsolution.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class UserRepository {
    private final HashMap<Integer, User> users = new HashMap<>();
    private int pointer = 1;

    public User getById(int id) {
        if (!users.containsKey(id)) throw new EntityNotFoundException();
        return users.get(id);
    }

    public List<User> getAllByDate(LocalDate from, LocalDate to) {
        return users
                .values()
                .stream()
                .filter(user -> user.getBirthday().isAfter(from) && user.getBirthday().isBefore(to)).toList();
    }

    public void create(User user) {
        user.setId(pointer);
        users.put(pointer, user);
        pointer++;
    }

    public void delete(int id) {
        users.remove(id);
    }

    public void update(int id, User user) {
        if (!users.containsKey(id)) throw new EntityNotFoundException();
        users.remove(id);
        users.put(id, user);
    }

    public void patch(int id, UserNullable user) {
        if (!users.containsKey(id)) throw new EntityNotFoundException();
        users.get(id).updateFromNullable(user);
    }
}
