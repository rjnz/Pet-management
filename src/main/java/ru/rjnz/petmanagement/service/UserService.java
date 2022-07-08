package ru.rjnz.petmanagement.service;

import org.springframework.stereotype.Service;
import ru.rjnz.petmanagement.model.User;
import ru.rjnz.petmanagement.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUser(String name, String password) {
        return repository.getByNameAndPassword(name, password);
    }

    public User save(User user) {
        return repository.save(user);
    }
}
