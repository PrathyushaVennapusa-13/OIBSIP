package com.lms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lms.Iservice.IBorrowService;
import com.lms.dto.BorrowDto;
import com.lms.dto.UserDto;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/borrow")
public class BorrowController {


    private final IBorrowService borrowService;



    public BorrowController(
            IBorrowService borrowService) {

        this.borrowService = borrowService;

    }







    // =====================================
    // ADMIN VIEW ALL ISSUED BOOKS
    // =====================================

    @GetMapping("/all")
    public String getAllBorrows(
            Model model) {



        List<BorrowDto> borrows =
                borrowService.getAllBorrows();



        model.addAttribute(
                "borrows",
                borrows);



        return "admin/borrows";

    }








    // =====================================
    // USER VIEW BORROWED BOOKS
    // =====================================

 // =====================================
 // USER VIEW BORROWED BOOKS
 // =====================================

 @GetMapping("/user/{userId}")
 public String getBorrowsByUser(
         @PathVariable Integer userId,
         Model model) {


     List<BorrowDto> borrows =
             borrowService.getBorrowsByUser(userId);



     model.addAttribute(
             "borrows",
             borrows
     );


     return "user/borrows";

 }








    // =====================================
    // USER REQUEST RETURN
    // =====================================

    @PostMapping("/requestReturn/{borrowId}")
    public String requestReturn(
            @PathVariable Integer borrowId,
            HttpSession session) {



        BorrowDto borrow =
                borrowService
                .requestReturn(borrowId);



        Integer userId =
                borrow.getUserId();



        return "redirect:/borrow/user/" 
                + userId;

    }








    // =====================================
    // ADMIN APPROVE RETURN
    // =====================================

    @PostMapping("/approveReturn/{borrowId}")
    public String approveReturn(
            @PathVariable Integer borrowId) {



        borrowService
        .approveReturn(borrowId);



        return "redirect:/borrow/all";

    }








    // =====================================
    // VIEW SINGLE BORROW
    // =====================================

    @GetMapping("/{borrowId}")
    public String getBorrowById(
            @PathVariable Integer borrowId,
            Model model) {



        BorrowDto borrow =
                borrowService
                .getBorrowById(borrowId);



        model.addAttribute(
                "borrow",
                borrow);



        return "admin/borrowDetails";

    }


}