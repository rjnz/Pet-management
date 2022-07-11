package ru.rjnz.petmanagement.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rjnz.petmanagement.model.User;

@Repository
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    User getByNameAndPassword(String name, String password);
}

