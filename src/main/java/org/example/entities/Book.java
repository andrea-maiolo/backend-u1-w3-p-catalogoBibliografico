package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends ProdottoBiblioteca {
    private String author;
    private String genre;

    public Book() {
    }

    public Book(long isbn, String title, int publicationYear, int numberOfPages, String author, String genre) {
        super(isbn, title, publicationYear, numberOfPages);
        this.author = author;
        this.genre = genre;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
