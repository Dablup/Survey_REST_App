package com.example.akvelon.akvelon.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="dateOfStart")
    private Date dateOfStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="dateOfEnd")
    private Date dateOfEnd;

    @NotNull(message = "active type can only be true or false")
    @Column(name="active")
    private boolean active;

    public Survey(String name, Date dateOfStart, Date dateOfEnd, boolean active) {
        this.name = name;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.active = active;
    }

    public Survey() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

