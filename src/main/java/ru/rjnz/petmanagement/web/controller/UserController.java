package ru.rjnz.petmanagement.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.rjnz.petmanagement.model.User;
import ru.rjnz.petmanagement.service.UserService;

import java.net.URI;

import static ru.rjnz.petmanagement.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = UserController.PROFILE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String PROFILE_URL = "/profile";

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        checkNew(user);
        User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PROFILE_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
