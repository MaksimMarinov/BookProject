package com.example.bookproject.model.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersonBaseEntity extends BaseEntity {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public PersonBaseEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonBaseEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
