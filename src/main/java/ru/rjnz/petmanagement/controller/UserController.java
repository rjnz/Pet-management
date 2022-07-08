package ru.rjnz.petmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rjnz.petmanagement.AuthorizedUser;
import ru.rjnz.petmanagement.model.User;
import ru.rjnz.petmanagement.service.UserService;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        return login(service.save(user));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody User user) {
        User dbUser = service.findUser(user.getName(), user.getPassword());
        AuthorizedUser.user = dbUser;
        return dbUser;
    }

    @GetMapping(value = "/logout")
    public void logout() {
        AuthorizedUser.user = null;
    }
}
