package com.trumpxmusk.libreriadelibros.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @Id
    @JsonProperty("id")
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Person> authors = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_languages",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages = new ArrayList<>();
    
    @Column(name = "download_count")
    @JsonProperty("download_count")
    private int downloadCount;

    public void addAuthor(Person author) {
        authors.add(author);
    }

    public void removeAuthor(Person author) {
        authors.remove(author);
    }

    public void addLanguage(Language language) {
        languages.add(language);
    }

    public void removeLanguage(Language language) {
        languages.remove(language);
    }

    @Override
    public String toString() {
        return String.format("""
            Libro: %s
            ID: %d
            Autores: %s
            Idiomas: %s
            Descargas: %d
            """, 
            title, 
            id, 
            authors != null ? authors.stream().map(Person::getName).toList() : "N/A",
            languages != null ? languages.stream().map(Language::getId).toList() : "N/A",
            downloadCount
        );
    }
} 