package com.leokr.mealideas.model;

import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
@Table(name="types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Type() { super(); }

    public Type(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id + " : " + this.name;
    }
}
