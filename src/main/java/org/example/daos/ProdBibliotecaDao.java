package org.example.daos;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Book;
import org.example.entities.ProdottoBiblioteca;

import java.util.List;

public class ProdBibliotecaDao {
    private EntityManager entityManager;

    public ProdBibliotecaDao(EntityManager em) {
        this.entityManager = em;
    }

    public ProdottoBiblioteca getByIsbnCode(long isbn) {
        TypedQuery<ProdottoBiblioteca> query = entityManager.createQuery("SELECT pb FROM ProdottoBiblioteca pb WHERE pb.isbn = :isbn",
                ProdottoBiblioteca.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    public List<ProdottoBiblioteca> getByPublicationYear(int publicationYear) {
        TypedQuery<ProdottoBiblioteca> query = entityManager.createQuery("SELECT pb FROM ProdottoBiblioteca pb WHERE pb.publicationYear = :publicationYear",
                ProdottoBiblioteca.class);
        query.setParameter("publicationYear", publicationYear);
        return query.getResultList();
    }

    public List<Book> getByAuthor(String author) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public List<ProdottoBiblioteca> getByPartialTitle(String title) {
        TypedQuery<ProdottoBiblioteca> query = entityManager.createQuery("SELECT pb FROM ProdottoBiblioteca pb WHERE pb.title ILIKE :title",
                ProdottoBiblioteca.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

}
