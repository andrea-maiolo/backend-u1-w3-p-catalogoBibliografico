package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private ProdottoBiblioteca borrowedItem;

    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate actualReturn;

    public Loan() {
    }

    public Loan(User user, ProdottoBiblioteca borrowedItem, LocalDate startDate) {
        this.user = user;
        this.borrowedItem = borrowedItem;
        this.startDate = startDate;
        this.dueDate = startDate.plusDays(30);
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }


    public ProdottoBiblioteca getBorrowedItem() {
        return borrowedItem;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getActualReturn() {
        return actualReturn;
    }

    public void setActualReturn(LocalDate actualReturn) {
        this.actualReturn = actualReturn;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", borrowedItem=" + borrowedItem +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", actualReturn=" + actualReturn +
                '}';
    }
}
