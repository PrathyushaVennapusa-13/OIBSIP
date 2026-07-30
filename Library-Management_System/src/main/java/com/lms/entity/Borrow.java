package com.lms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_id_generator")
    @SequenceGenerator(
            name = "borrow_id_generator",
            initialValue = 501,
            allocationSize = 1)
    private int borrowId;

    private LocalDateTime issueDate;

    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="book_id")
    private Book book;
}