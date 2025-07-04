package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private LocalDate dob;
    private long membershipCardNumber;


    public User() {
    }

    public User(String name, String surname, LocalDate dob, long membershipCardNumber) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.membershipCardNumber = membershipCardNumber;
    }

    public UUID getId() {
        return id;
    }


    public long getMembershipCardNumber() {
        return membershipCardNumber;
    }

    public void setMembershipCardNumber(long membershipCardNumber) {
        this.membershipCardNumber = membershipCardNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", membershipCardNumber=" + membershipCardNumber +
                '}';
    }
}
