package com.lms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lms.util.BorrowStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowDto {

    private Integer borrowId;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private BorrowStatus status;

    private double fineAmount;


    private Integer userId;

    private Integer bookId;

    private String bookTitle;

}