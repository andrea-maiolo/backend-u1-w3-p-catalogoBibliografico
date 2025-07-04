package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
