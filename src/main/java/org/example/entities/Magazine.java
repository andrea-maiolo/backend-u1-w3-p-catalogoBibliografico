package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.example.enums.Frequency;

@Entity
@Table(name = "magazines")
public class Magazine extends ProdottoBiblioteca {
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    public Magazine() {
    }

    public Magazine(long isbn, String title, int publicationYear, int numberOfPages, Frequency frequency) {
        super(isbn, title, publicationYear, numberOfPages);
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "frequency=" + frequency +
                ", id=" + id +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
