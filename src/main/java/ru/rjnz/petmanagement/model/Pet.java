package ru.rjnz.petmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.rjnz.petmanagement.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pets", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "name"}, name = "pets_unique_name_user_idx")})
public class Pet extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    private User user;

    public Pet(int id, String name, Type type, Sex sex) {
        super(id, name);
        this.type = type;
        this.sex = sex;
    }

    public Pet() {
        super();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Sex getSex() {
        return sex;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
