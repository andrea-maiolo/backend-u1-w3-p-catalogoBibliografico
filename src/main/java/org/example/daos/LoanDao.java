package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.customexceptions.NotfoundException;
import org.example.entities.ProdottoBiblioteca;

import java.util.List;

public class LoanDao {
    private EntityManager em;

    public LoanDao(EntityManager em) {
        this.em = em;
    }

    public List<ProdottoBiblioteca> getCurrentlyBorrowedItems(long membershipCardNumber) {
        TypedQuery<ProdottoBiblioteca> query = em.createQuery(
                "SELECT l.borrowedItem FROM Loan l WHERE l.user.membershipCardNumber = :cardNumber AND l.actualReturn IS NULL",
                ProdottoBiblioteca.class
        );
        query.setParameter("cardNumber", membershipCardNumber);
        if (query.getResultList().isEmpty()) {
            throw new NotfoundException("no item to return");
        }
        return query.getResultList();
    }

}
