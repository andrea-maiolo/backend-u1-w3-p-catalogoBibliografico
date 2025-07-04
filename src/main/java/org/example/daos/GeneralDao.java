package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.User;

import java.util.UUID;

public class GeneralDao<T> {
    private EntityManager entityManager;

    public GeneralDao(EntityManager em) {
        this.entityManager = em;
    }

    public void saveInDb(T objToSave) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(objToSave);
        transaction.commit();
    }

    public User getUserById(String uuid) {
        UUID actualId = UUID.fromString(uuid);
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :uuid", User.class);
        query.setParameter("uuid", actualId);
        return query.getSingleResult();
    }
}
