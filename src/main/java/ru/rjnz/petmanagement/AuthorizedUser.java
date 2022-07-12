package ru.rjnz.petmanagement;

import ru.rjnz.petmanagement.model.User;

import java.util.HashSet;

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
                new HashSet<>()
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
