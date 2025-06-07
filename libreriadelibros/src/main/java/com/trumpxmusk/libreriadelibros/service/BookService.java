package com.trumpxmusk.libreriadelibros.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trumpxmusk.libreriadelibros.model.Book;
import com.trumpxmusk.libreriadelibros.model.Person;
import com.trumpxmusk.libreriadelibros.repository.BookRepository;
import com.trumpxmusk.libreriadelibros.repository.PersonRepository;
import com.trumpxmusk.libreriadelibros.utils.CustomHttpClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final CustomHttpClient httpClient;
    
    public String searchAndSaveBook(String title) {
        try {
            List<Book> books = httpClient.getBookByTitle(title);
            
            if (books == null || books.isEmpty()) {
                return "No se encontraron libros con ese título.";
            }
            
            Book book = books.get(0);
            
            List<Book> existingBooks = bookRepository.findByTitleContainingIgnoreCase(book.getTitle());
            if (!existingBooks.isEmpty()) {
                return "El libro '" + book.getTitle() + "' ya existe en la base de datos.";
            }
            
            bookRepository.save(book);
            return "Libro '" + book.getTitle() + "' guardado exitosamente en la base de datos.";
            
        } catch (IOException | InterruptedException e) {
            return "Error al buscar el libro: " + e.getMessage();
        }
    }

    public List<Book> listarTodosLosLibros() {
        return bookRepository.findAll();
    }

    public List<Person> listarTodosLosAutores() {
        return personRepository.findAll();
    }

    public List<Person> listarAutoresVivosEnAño(int año) {
        return personRepository.findByBirthYearLessThanAndDeathYearGreaterThanOrDeathYearIsNull(año, año);
    }

    public List<Book> listarLibrosPorIdioma(String codigoIdioma) {
        return bookRepository.findByLanguagesId(codigoIdioma);
    }
} 