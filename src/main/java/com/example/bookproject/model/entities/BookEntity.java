package com.example.bookproject.model.entities;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity{
    private String name;
    private AuthorEntity author;
    private BigDecimal price;
    private String description;
    private CategoryEntity category;
    private List<CommentEntity> comments;


    @OneToMany
    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    @Column(name="name", nullable = false)
    public String getName() {
        return name;
    }

    public BookEntity setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    public AuthorEntity getAuthor() {
        return author;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }

    @Column(name = "price")
    @DecimalMin(value = "1")
    public BigDecimal getPrice() {
        return price;
    }

    public BookEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public BookEntity setDescription(String description) {
        this.description = description;
        return this;
    }
    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public BookEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }
}
