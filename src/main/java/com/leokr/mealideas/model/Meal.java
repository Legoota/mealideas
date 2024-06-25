package com.leokr.mealideas.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@Entity
@Table(name="meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @CreatedDate
    private Date date_add;
    @LastModifiedDate
    private Date date_lastuse;
    private int counter;
    private long type;
    private String notes;

    public Meal() { super(); }

    public Meal(String name, Date date_add, Date date_lastuse, int counter, long type, String notes) {
        this.name = name;
        this.date_add = date_add;
        this.date_lastuse = date_lastuse;
        this.counter = counter;
        this.type = type;
        this.notes = notes;
    }

    public Meal(String name, Date date_add, int counter, long type, String notes) {
        this.name = name;
        this.date_add = date_add;
        this.date_lastuse = null;
        this.counter = counter;
        this.type = type;
        this.notes = notes;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getDate_add() {
        return this.date_add;
    }

    public Date getDate_lastuse() {
        return this.date_lastuse;
    }

    public int getCounter() {
        return this.counter;
    }

    public long getType() {
        return this.type;
    }
    public String getNotes() { return this.notes; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate_add(Date date_add) {
        this.date_add = date_add;
    }

    public void setDate_lastuse(Date date_lastuse) {
        this.date_lastuse = date_lastuse;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setType(long type) {
        this.type = type;
    }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return this.id + " : " + this.name + " de type " + this.type + " utilisé " + this.counter + " fois";
    }
}
