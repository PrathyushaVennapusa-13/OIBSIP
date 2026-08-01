package com.lms.Iservice;

import java.util.List;

import com.lms.dto.ReservationDto;

public interface IReservationService {

    // ==============================
    // USER SEND BORROW REQUEST
    // ==============================

    ReservationDto reserveBook(Integer userId, Integer bookId);

    // ==============================
    // ADMIN APPROVE REQUEST
    // ==============================

    ReservationDto approveReservation(Integer reservationId);

    // ==============================
    // ADMIN REJECT REQUEST
    // ==============================

    ReservationDto rejectReservation(Integer reservationId);

    // ==============================
    // ADMIN VIEW PENDING REQUESTS
    // ==============================

    List<ReservationDto> getPendingReservations();

    // ==============================
    // ADMIN VIEW ALL REQUESTS
    // ==============================

    List<ReservationDto> getAllReservations();

    // ==============================
    // USER VIEW OWN REQUESTS
    // ==============================

    List<ReservationDto> getReservationsByUser(Integer userId);

    // ==============================
    // VIEW SINGLE REQUEST
    // ==============================

    ReservationDto getReservationById(Integer reservationId);

    // ==============================
    // USER CANCEL REQUEST
    // ==============================

    void cancelReservation(Integer reservationId);

}