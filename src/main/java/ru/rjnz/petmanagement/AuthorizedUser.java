package ru.rjnz.petmanagement;

import ru.rjnz.petmanagement.model.Role;
import ru.rjnz.petmanagement.model.User;

import java.util.Set;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public AuthorizedUser(User user) {
        super(
                user.getName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Set.of(Role.USER)
        );
        this.user = user;
    }

    public int getId() {
        return user.id();
    }

    public void update(User newUser) {
        user = newUser;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
