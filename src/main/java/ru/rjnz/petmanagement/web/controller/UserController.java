package ru.rjnz.petmanagement.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.rjnz.petmanagement.model.User;
import ru.rjnz.petmanagement.security.jwt.JwtTokenProvider;
import ru.rjnz.petmanagement.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static ru.rjnz.petmanagement.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = UserController.PROFILE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String PROFILE_URL = "/rest/profile";

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider provider;

    public UserController(UserService service, AuthenticationManager authenticationManager, JwtTokenProvider provider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.provider = provider;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> register(@RequestBody User user) {
        checkNew(user);
        User created = service.create(user);
        return login(created);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())));
        String token = provider.createToken(user.getName(), user.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
        handler.logout(request, response, null);
    }
}
