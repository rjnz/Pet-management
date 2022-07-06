package ru.rjnz.petmanagement.model;

public class Pet extends AbstractEntity {
    private final Type type;
    private final Sex sex;

    protected Pet(int id, String name, Type type, Sex sex) {
        super(id, name);
        this.type = type;
        this.sex = sex;
    }
}
