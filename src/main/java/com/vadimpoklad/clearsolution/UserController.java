package com.vadimpoklad.clearsolution;

import com.vadimpoklad.clearsolution.entities.User;
import com.vadimpoklad.clearsolution.entities.UserNullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> getAll(@RequestParam(required = false, defaultValue = "0001-01-01") LocalDate from,
                             @RequestParam(required = false, defaultValue = "9999-12-31") LocalDate to) {
        return userService.getAllByDate(from, to);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid User user) {
        userService.create(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Valid User user) {
        userService.update(id, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void patch(@PathVariable int id, @RequestBody @Valid UserNullable user) {
        userService.patch(id, user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
}
