package ru.rjnz.petmanagement.model;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
    public User(int id, String name) {
        super(id, name);
    }

    public User() {
        super();
    }
}
