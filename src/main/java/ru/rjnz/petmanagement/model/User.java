package ru.rjnz.petmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"}, name = "users_unique_name_idx")})
public class User extends AbstractEntity implements Serializable {
    @NotBlank
    @Size(min = 2, max = 12)
    @Column(name = "password", nullable = false)
    private String password;

    public User(int id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    public User() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
