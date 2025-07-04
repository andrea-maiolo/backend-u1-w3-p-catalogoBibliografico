package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_biblioteca");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();

        
        //giusto per convenzione
        entityManager.close();
        emf.close();
    }
}
