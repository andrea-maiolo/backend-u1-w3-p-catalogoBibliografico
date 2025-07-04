package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.daos.MyDao;
import org.example.entities.Book;
import org.example.entities.ProdottoBiblioteca;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_biblioteca");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
        MyDao myDao = new MyDao<>(entityManager);


        Book book1 = new Book("9780142437247", "moby dick", 1851,
                635, "Herman Melville", "adventure");

        //myDao.saveInDb(book1);

        //ProdottoBiblioteca bookFromDb = (ProdottoBiblioteca) myDao.getWithIsbn(ProdottoBiblioteca.class, "9780142437247");
        ProdottoBiblioteca bookFromDb = myDao.getBokByIsbn("9780142437247");
        System.out.println(bookFromDb);


        //giusto per convenzione
        entityManager.close();
        emf.close();
    }
}
