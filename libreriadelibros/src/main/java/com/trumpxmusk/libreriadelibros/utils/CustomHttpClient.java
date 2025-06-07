package com.trumpxmusk.libreriadelibros.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trumpxmusk.libreriadelibros.dto.BooksResponseDto;
import com.trumpxmusk.libreriadelibros.model.Book;

@Component
public class CustomHttpClient {
    private final HttpClient client;
    private final String route = "https://gutendex.com/";
    private final ObjectMapper objectMapper;

    private HttpRequest uriBuilder(String endpoint) {
        return HttpRequest.newBuilder()
            .uri(URI.create(route + endpoint))
            .header("Accept", "application/json")
            .header("User-Agent", "Java-HttpClient")
            .header("Content-Type", "application/json")
            .GET()
            .build();
    }

    public CustomHttpClient() {
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public BooksResponseDto getBooks() throws IOException, InterruptedException {
        HttpRequest request = uriBuilder("books");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), BooksResponseDto.class);
    }

    public Book getBookById(int bookId) throws IOException, InterruptedException {
        HttpRequest request = uriBuilder("books/" + bookId);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Book.class);
    }

    public List<Book> getBookByTitle(String bookName) throws IOException, InterruptedException {
        HttpRequest request = uriBuilder("books?search=" + bookName);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        BooksResponseDto booksResponse = objectMapper.readValue(response.body(), BooksResponseDto.class);
        return booksResponse.getResults();
    }
} 