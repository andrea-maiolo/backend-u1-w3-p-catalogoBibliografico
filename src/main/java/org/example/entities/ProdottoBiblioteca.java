package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "b_products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProdottoBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;
    protected long isbn;
    protected String title;
    protected int publicationYear;
    protected int numberOfPages;


    public ProdottoBiblioteca() {
    }

    public ProdottoBiblioteca(long isbn, String title, int publicationYear, int numberOfPages) {
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.title = title;
    }

    public long getId() {
        return id;
    }


    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }


    @Override
    public String toString() {
        return "ProdottoBiblioteca{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
