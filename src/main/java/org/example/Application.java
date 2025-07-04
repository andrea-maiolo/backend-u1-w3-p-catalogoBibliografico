package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import org.example.daos.GeneralDao;
import org.example.daos.ProdBibliotecaDao;
import org.example.entities.Book;
import org.example.entities.Magazine;
import org.example.entities.ProdottoBiblioteca;
import org.example.entities.User;
import org.example.enums.Frequency;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_biblioteca");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
        GeneralDao generalDao = new GeneralDao<>(entityManager);
        ProdBibliotecaDao prodBibliotecaDao = new ProdBibliotecaDao(entityManager);
        Faker faker = new Faker(Locale.ITALY);
        Random random = new Random();

        Supplier<Book> bookSupplier = () -> {
            String bTitle = faker.book().title();
            String bAuthor = faker.book().author();
            String bGenre = faker.book().genre();
            long bIsbn = random.nextLong(100000000, 900000000);
            int bPublicationYear = faker.number().numberBetween(1800, 2025);
            int bNumberOfPages = faker.number().numberBetween(100, 1000);

            Book newBook = new Book(bIsbn, bTitle, bPublicationYear, bNumberOfPages, bAuthor, bGenre);
            return newBook;
        };


        Supplier<Magazine> magazineSupplier = () -> {
            long mIsbn = random.nextLong(100000000, 900000000);
            String mTitle = faker.dcComics().title();
            int mPublicatioYear = faker.number().numberBetween(1960, 2025);
            int mNumberOfPages = faker.number().numberBetween(20, 100);
            Frequency mfreq = Frequency.values()[faker.number().numberBetween(0, 3)];

            Magazine newMagazine = new Magazine(mIsbn, mTitle, mPublicatioYear, mNumberOfPages, mfreq);
            return newMagazine;
        };


        //aggiunta elementi al catalogo
//        for (int i = 0; i < 5; i++) {
//            generalDao.saveInDb((bookSupplier.get()));
//            generalDao.saveInDb(magazineSupplier.get());
//        }

        Supplier<User> userSupplier = () -> {
            String uName = faker.name().firstName();
            String uSurname = faker.name().lastName();
            LocalDate uDob = faker.date().birthdayLocalDate();
            long uMembershipCardNumber = random.nextLong(100000000, 900000000);
            User newUser = new User(uName, uSurname, uDob, uMembershipCardNumber);
            return newUser;
        };

//            aggiunta users
//        for (int i = 0; i < 10; i++) {
//            generalDao.saveInDb(userSupplier.get());
//        }

        // metodo con query per avere prodotto biblio dal codice isbn
        ProdottoBiblioteca prodfromDb = prodBibliotecaDao.getByIsbnCode(748443023);
        System.out.println(prodfromDb);
        ProdottoBiblioteca prodFromDb2 = prodBibliotecaDao.getByIsbnCode(138807158);
        System.out.println(prodFromDb2);


        //giusto per convenzione
        entityManager.close();
        emf.close();
    }
}
