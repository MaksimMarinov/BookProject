package com.example.bookproject.model.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HumanEntity extends BaseEntity {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public HumanEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public HumanEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
