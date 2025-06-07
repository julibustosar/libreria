package com.trumpxmusk.libreriadelibros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trumpxmusk.libreriadelibros.model.Book;
import com.trumpxmusk.libreriadelibros.model.Person;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    List<Book> findByAuthorsContaining(Person author);
    
    List<Book> findByLanguagesId(String languageId);
    
    List<Book> findByDownloadCountGreaterThanEqual(int minDownloads);
} 