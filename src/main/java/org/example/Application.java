package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import org.example.customexceptions.NotfoundException;
import org.example.daos.GeneralDao;
import org.example.daos.LoanDao;
import org.example.daos.ProdBibliotecaDao;
import org.example.entities.*;
import org.example.enums.Frequency;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_biblioteca");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
        GeneralDao generalDao = new GeneralDao<>(entityManager);
        ProdBibliotecaDao prodBibliotecaDao = new ProdBibliotecaDao(entityManager);
        LoanDao loanDao = new LoanDao(entityManager);
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
        try {
            ProdottoBiblioteca prodfromDb = prodBibliotecaDao.getByIsbnCode(748443023);
            // System.out.println(prodfromDb);
            ProdottoBiblioteca prodFromDb2 = prodBibliotecaDao.getByIsbnCode(138807158);
            //System.out.println(prodFromDb2);

        } catch (NotfoundException ex) {
            throw new NotfoundException("prodotto non trovato, controlla il codice");
        }

        //      metodo per lista prodotti biblio dall anno
        try {
            List<ProdottoBiblioteca> prodDb = prodBibliotecaDao.getByPublicationYear(1954);
            // prodDb.forEach(System.out::println);
        } catch (NotfoundException ex) {
            throw new NotfoundException("prodotto non trovato, controlla anno");
        }

        //metodo per lista libri per autore
        try {
            List<Book> bookListSameAuthor = prodBibliotecaDao.getByAuthor("Eriberto Vitale");
            // bookListSameAuthor.forEach(System.out::println);
        } catch (NotfoundException ex) {
            throw new NotfoundException("prodotto non trovato, controlla anno");
        }

        //metodo per ricerca con titolo anche parziale
        try {
            List<ProdottoBiblioteca> booksByTitle = prodBibliotecaDao.getByPartialTitle("the");
            // booksByTitle.forEach(System.out::println);
        } catch (NotfoundException ex) {
            throw new NotfoundException("trovato nulla ,prova ancora");
        }

        //metodo per cancellare dal db da rivedere perche e sempre ok ma non cancella niente
//        try {
//            prodBibliotecaDao.deleteByIsbn(466490830);
//            System.out.println("deleted successfully");
//        } catch (NotAbleToDeleteException ex) {
//            throw new NotAbleToDeleteException("delete oparation was not successful");
//        }

        User userFromDb = generalDao.getUserById("29ca16bb-1e11-458e-bb87-01a4876c0412");
        //System.out.println(userFromDb);

        ProdottoBiblioteca prodfromDb3 = prodBibliotecaDao.getById(102);
        //System.out.println(prodfromDb3);

        Loan newLoan = new Loan(userFromDb, prodfromDb3, LocalDate.now());
        // System.out.println(newLoan);

        ProdottoBiblioteca prodfromDb4 = prodBibliotecaDao.getById(103);
        User userFromDb2 = generalDao.getUserById("36dbf4ea-44fe-4780-97d4-d490c0d62ed4");
        // System.out.println(userFromDb2);
        Loan newLoan2 = new Loan(userFromDb2, prodfromDb4, LocalDate.of(2024, 03, 03));
        newLoan2.setActualReturn(LocalDate.of(2024, 03, 20));

        //generalDao.saveInDb(newLoan2);

        //generalDao.saveInDb(newLoan);

        //metodo per vedere quali item sono in prestito data la tessera dello user
        //con eccezione
        try {
            List<ProdottoBiblioteca> borrowedItems = loanDao.getCurrentlyBorrowedItems(658102119);
            borrowedItems.forEach(p -> System.out.println(p));
        } catch (NotfoundException ex) {
            throw new NotfoundException("no items are borrowed by this user");
        }
//senza eccezione
        try {
            List<ProdottoBiblioteca> borrowedItems = loanDao.getCurrentlyBorrowedItems(644488565);
            borrowedItems.forEach(p -> System.out.println(p));
        } catch (NotfoundException ex) {
            throw new NotfoundException("no items are borrowed by this user");
        }

        //giusto per convenzione
        entityManager.close();
        emf.close();
    }
}
