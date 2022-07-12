package ru.rjnz.petmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.rjnz.petmanagement.AuthorizedUser;
import ru.rjnz.petmanagement.model.User;
import ru.rjnz.petmanagement.repository.CrudUserRepository;

@Service
public class UserService implements UserDetailsService {
    private final CrudUserRepository repository;

    public UserService(CrudUserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
