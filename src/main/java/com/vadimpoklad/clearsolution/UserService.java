package com.vadimpoklad.clearsolution;

import com.vadimpoklad.clearsolution.entities.User;
import com.vadimpoklad.clearsolution.entities.UserNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(int id) {
        return userRepository.getById(id);
    }

    public List<User> getAllByDate(LocalDate from, LocalDate to) {
        return userRepository.getAllByDate(from, to);
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void update(int id, User user) {
        userRepository.update(id, user);
    }

    public void patch(int id, UserNullable user) {
        userRepository.patch(id, user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }
}
