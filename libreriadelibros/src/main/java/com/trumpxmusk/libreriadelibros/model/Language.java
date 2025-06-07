package com.trumpxmusk.libreriadelibros.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "languages")
public class Language {
    @Id
    private String id; // El código del idioma (en, es, fr, etc.)

    @ManyToMany(mappedBy = "languages")
    private List<Book> books = new ArrayList<>();

    // Constructor
    public Language() {}

    public Language(String id) {
        this.id = id;
    }

    public void addBook(Book book) {
        books.add(book);
        book.addLanguage(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.removeLanguage(this);
    }

    @Override
    public String toString() {
        return id;
    }
} 