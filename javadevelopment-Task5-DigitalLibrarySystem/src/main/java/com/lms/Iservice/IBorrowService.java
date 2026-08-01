package com.lms.Iservice;

import java.util.List;

import com.lms.dto.BorrowDto;

public interface IBorrowService {

    // ==============================
    // ADMIN ISSUED BOOK DETAILS
    // ==============================

    BorrowDto getBorrowById(Integer borrowId);

    List<BorrowDto> getAllBorrows();

    List<BorrowDto> getBorrowsByUser(Integer userId);

    // ==============================
    // USER REQUEST RETURN
    // ==============================

    BorrowDto requestReturn(Integer borrowId);

    // ==============================
    // ADMIN APPROVE RETURN
    // ==============================

    BorrowDto approveReturn(Integer borrowId);

    // ==============================
    // CALCULATE FINE
    // ==============================

    double calculateFine(Integer borrowId);

}