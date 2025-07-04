package org.example.daos;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

}
