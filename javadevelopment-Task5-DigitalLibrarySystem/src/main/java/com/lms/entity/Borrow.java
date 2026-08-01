package com.lms.entity;

import java.time.LocalDate;

import com.lms.util.BorrowStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq")
    @SequenceGenerator(
            name = "borrow_seq",
            sequenceName = "borrow_sequence",
            initialValue = 4000,
            allocationSize = 1
    )
    private Integer borrowId;

    // Date when admin issues the book
    private LocalDate issueDate;

    // Last date to return the book
    private LocalDate dueDate;

    // Date when admin accepts the returned book
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    // Fine in rupees
    private double fineAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}