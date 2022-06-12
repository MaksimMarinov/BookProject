package com.example.bookproject.model.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    private String name;
}
