package ru.rjnz.petmanagement.model;

public abstract class AbstractEntity {
    protected int id;
    protected String name;

    protected AbstractEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
