package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import org.example.daos.GeneralDao;
import org.example.entities.Book;
import org.example.entities.Magazine;
import org.example.entities.User;
import org.example.enums.Frequency;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.Supplier;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_biblioteca");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
        GeneralDao generalDao = new GeneralDao<>(entityManager);
        Faker faker = new Faker(Locale.ITALY);

        Supplier<Book> bookSupplier = () -> {
            String bTitle = faker.book().title();
            String bAuthor = faker.book().author();
            String bGenre = faker.book().genre();
            String bIsbn = String.valueOf(faker.hashCode());
            int bPublicationYear = faker.number().numberBetween(1800, 2025);
            int bNumberOfPages = faker.number().numberBetween(100, 1000);

            Book newBook = new Book(bIsbn, bTitle, bPublicationYear, bNumberOfPages, bAuthor, bGenre);
            return newBook;
        };


        Supplier<Magazine> magazineSupplier = () -> {
            String mIsbn = String.valueOf(faker.hashCode());
            String mTitle = faker.dcComics().title();
            int mPublicatioYear = faker.number().numberBetween(1960, 2025);
            int mNumberOfPages = faker.number().numberBetween(20, 100);
            Frequency mfreq = Frequency.values()[faker.number().numberBetween(0, 3)];

            Magazine newMagazine = new Magazine(mIsbn, mTitle, mPublicatioYear, mNumberOfPages, mfreq);
            return newMagazine;
        };

        Book book1 = new Book("9780142437247", "moby dick", 1851,
                635, "Herman Melville", "adventure");


        //aggiunta elementi al catalogo
//        for (int i = 0; i < 5; i++) {
//         generalDao.saveInDb((bookSupplier.get()));
//            generalDao.saveInDb(magazineSupplier.get());
//        }
//
        Supplier<User> userSupplier = () -> {
            String uName = faker.name().firstName();
            String uSurname = faker.name().lastName();
            LocalDate uDob = faker.date().birthdayLocalDate();
            int uMembershipCardNumber = faker.number().numberBetween(1000000, 900000);

            User newUser = new User(uName, uSurname, uDob, uMembershipCardNumber);
            return newUser;
        };

//            aggiunta users
//        for (int i = 0; i < 10; i++) {
//            generalDao.saveInDb(userSupplier.get());
//        }

        //ProdottoBiblioteca bookFromDb = (ProdottoBiblioteca) myDao.getWithIsbn(ProdottoBiblioteca.class, "9780142437247");
//        ProdottoBiblioteca bookFromDb = myDao.getBokByIsbn("9780142437247");
//        System.out.println(bookFromDb);


        //giusto per convenzione
        entityManager.close();
        emf.close();
    }
}
