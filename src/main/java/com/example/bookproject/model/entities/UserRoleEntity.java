package com.example.bookproject.model.entities;

import com.example.bookproject.model.enums.RoleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    RoleNameEnum role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public RoleNameEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(RoleNameEnum role) {
        this.role = role;
        return this;
    }
}
