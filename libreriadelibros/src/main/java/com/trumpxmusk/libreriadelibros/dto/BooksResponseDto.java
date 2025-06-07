package com.trumpxmusk.libreriadelibros.dto;

import java.util.List;

import com.trumpxmusk.libreriadelibros.model.Book;

public class BooksResponseDto {
    private int count;
    private String next;
    private String previous;
    private List<Book> results;

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public String getNext() { return next; }
    public void setNext(String next) { this.next = next; }

    public String getPrevious() { return previous; }
    public void setPrevious(String previous) { this.previous = previous; }

    public List<Book> getResults() { return results; }
    public void setResults(List<Book> results) { this.results = results; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total de libros: ").append(count).append("\n\n");
        if (results != null) {
            for (Book book : results) {
                sb.append(book.toString()).append("\n");
            }
        }
        return sb.toString();
    }
} 