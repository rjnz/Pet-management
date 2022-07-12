package ru.rjnz.petmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.rjnz.petmanagement.AuthorizedUser;
import ru.rjnz.petmanagement.model.User;
import ru.rjnz.petmanagement.repository.CrudUserRepository;
import ru.rjnz.petmanagement.security.LoginAttemptService;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements UserDetailsService {
    private final CrudUserRepository repository;
    private final LoginAttemptService loginAttemptService;
    private final HttpServletRequest request;

    public UserService(CrudUserRepository repository, LoginAttemptService loginAttemptService, HttpServletRequest request) {
        this.repository = repository;
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        User user = repository.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
