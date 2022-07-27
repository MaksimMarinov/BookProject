package com.example.bookproject.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends PersonBaseEntity {

    private String username;
    private String password;
    private String email;
    private List<UserRoleEntity> roles;
    private List<BookEntity> booksAdded;
    private List<CommentEntity> comments;


    @OneToMany
    public List<BookEntity> getBooksAdded() {
        return booksAdded;
    }

    public void setBooksAdded(List<BookEntity> booksAdded) {
        this.booksAdded = booksAdded;
    }

    @OneToMany
    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }



    @ManyToMany(fetch = FetchType.EAGER)
    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
