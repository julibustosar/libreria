package com.trumpxmusk.libreriadelibros.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "authors")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_year")
    @JsonProperty("birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    @JsonProperty("death_year")
    private Integer deathYear;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        book.addAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.removeAuthor(this);
    }

    @Override
    public String toString() {
        return String.format("%s (%s-%s)", 
            name,
            birthYear != null ? birthYear : "?",
            deathYear != null ? deathYear : "?"
        );
    }
} 