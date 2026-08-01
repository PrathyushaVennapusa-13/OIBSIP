package com.lms.dto;

import java.time.LocalDate;

import com.lms.util.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {

    private Integer reservationId;

    private LocalDate reservationDate;

    private ReservationStatus status;


    // User information
    private Integer userId;


    // Book information
    private Integer bookId;

    private String bookTitle;

    private String author;

}