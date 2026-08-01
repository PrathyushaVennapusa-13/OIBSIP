package com.lms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lms.Iservice.IReservationService;
import com.lms.dto.ReservationDto;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/reservation")
public class ReservationController {


    private final IReservationService reservationService;



    public ReservationController(
            IReservationService reservationService) {

        this.reservationService = reservationService;

    }






    // =====================================
    // USER SEND BORROW REQUEST
    // =====================================
    @PostMapping("/request")
    public String reserveBook(
            @RequestParam Integer userId,
            @RequestParam Integer bookId) {


        reservationService.reserveBook(
                userId,
                bookId
        );


        return "redirect:/reservation/user/" + userId;

    }






    // =====================================
    // USER VIEW OWN REQUESTS
    // =====================================

    @GetMapping("/user/{userId}")
    public String getReservationsByUser(
            @PathVariable Integer userId,
            Model model) {



        List<ReservationDto> reservations =
                reservationService
                .getReservationsByUser(userId);



        model.addAttribute(
                "reservations",
                reservations);



        model.addAttribute(
                "userId",
                userId);



        return "user/reservations";

    }








    // =====================================
    // ADMIN VIEW PENDING REQUESTS
    // =====================================

    @GetMapping("/pending")
    public String getPendingReservations(
            Model model) {



        List<ReservationDto> reservations =
                reservationService
                .getPendingReservations();



        model.addAttribute(
                "reservations",
                reservations);



        return "admin/pendingReservations";

    }








    // =====================================
    // ADMIN APPROVE
    // =====================================

    @PostMapping("/approve/{reservationId}")
    public String approveReservation(
            @PathVariable Integer reservationId) {



        reservationService
        .approveReservation(reservationId);



        return "redirect:/reservation/pending";

    }








    // =====================================
    // ADMIN REJECT
    // =====================================

    @PostMapping("/reject/{reservationId}")
    public String rejectReservation(
            @PathVariable Integer reservationId) {



        reservationService
        .rejectReservation(reservationId);



        return "redirect:/reservation/pending";

    }








    // =====================================
    // ADMIN VIEW ALL
    // =====================================

    @GetMapping("/all")
    public String getAllReservations(
            Model model) {



        List<ReservationDto> reservations =
                reservationService
                .getAllReservations();



        model.addAttribute(
                "reservations",
                reservations);



        return "admin/reservations";

    }








    // =====================================
    // VIEW SINGLE
    // =====================================

    @GetMapping("/{reservationId}")
    public String getReservationById(
            @PathVariable Integer reservationId,
            Model model) {



        ReservationDto reservation =
                reservationService
                .getReservationById(reservationId);



        model.addAttribute(
                "reservation",
                reservation);



        return "admin/reservationDetails";

    }








    // =====================================
    // USER CANCEL REQUEST
    // =====================================

    @GetMapping("/cancel/{reservationId}")
    public String cancelReservation(
            @PathVariable Integer reservationId,
            HttpSession session) {



        reservationService
        .cancelReservation(reservationId);



        return "redirect:/reservation/user/" +
                getUserId(session);

    }








    private Integer getUserId(
            HttpSession session){



        Object user =
                session.getAttribute("user");



        if(user != null){

            return ((com.lms.dto.UserDto)user)
                    .getUserId();

        }



        return 0;

    }


}