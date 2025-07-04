package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.ProdottoBiblioteca;

public class MyDao<T> {

    private EntityManager entityManager;

    public MyDao(EntityManager em) {
        this.entityManager = em;
    }

    public void saveInDb(T objToSave) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(objToSave);
        transaction.commit();
    }

//    public T getWithIsbn(Class<T> entityClass, String isbn) {
//        TypedQuery<T> query = entityManager.createQuery("SELECT bp FROM ProdottoBiblioteca bp WHERE bp.isbn = :isbn", entityClass.getClass());
//        query.setParameter("isbn", isbn);
//        return query.getSingleResult();
//
//    }

    public ProdottoBiblioteca getBokByIsbn(String isbn) {
        TypedQuery<ProdottoBiblioteca> query = entityManager.createQuery("SELECT bp FROM ProdottoBiblioteca bp WHERE bp.isbn = :isbn", ProdottoBiblioteca.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

}
