package com.lms.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_id_generator")
    @SequenceGenerator(
            name = "book_id_generator",
            initialValue = 301,
            allocationSize = 1)
    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private int noOfCopy;
    private int availableCopies;
    private int issuedCopies;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @OneToMany(mappedBy ="book")
    private List<Borrow> borrows;
}
