package com.lms.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.Iservice.IReservationService;
import com.lms.dto.ReservationDto;
import com.lms.entity.Book;
import com.lms.entity.Borrow;
import com.lms.entity.Reservation;
import com.lms.entity.User;
import com.lms.exception.BookAvailableException;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.ReservationNotFoundException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.BorrowRepository;
import com.lms.repository.ReservationRepository;
import com.lms.repository.UserRepository;
import com.lms.util.BorrowStatus;
import com.lms.util.ReservationStatus;

import jakarta.transaction.Transactional;

@Service
public class ReservationService implements IReservationService {


    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;


    public ReservationService(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            BookRepository bookRepository,
            BorrowRepository borrowRepository) {

        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }



    @Override
    public ReservationDto reserveBook(Integer userId, Integer bookId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                new UserNotFoundException("User Not Found"));


        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                new BookNotFoundException("Book Not Found"));



        if(book.getAvailableQuantity() <= 0) {

            throw new BookAvailableException(
                    "Book is not available");
        }



        List<Reservation> list =
                reservationRepository.findByUserUserId(userId);



        for(Reservation r : list) {


            if(r.getBook().getBookId().equals(bookId)
                    &&
               r.getStatus()==ReservationStatus.PENDING) {


                throw new RuntimeException(
                        "Request already exists");
            }
        }



        Reservation reservation = new Reservation();

        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setReservationDate(LocalDate.now());
        reservation.setStatus(ReservationStatus.PENDING);



        Reservation saved =
                reservationRepository.save(reservation);


        return convertToDto(saved);

    }






    @Override
    @Transactional
    public ReservationDto approveReservation(Integer reservationId) {


        Reservation reservation =
                reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                new ReservationNotFoundException(
                        "Reservation Not Found"));



        if(reservation.getStatus()!=ReservationStatus.PENDING){

            throw new RuntimeException(
                    "Already processed");

        }



        Book book = reservation.getBook();



        if(book.getAvailableQuantity()<=0){

            throw new BookAvailableException(
                    "Book unavailable");

        }



        Borrow borrow = new Borrow();


        borrow.setUser(reservation.getUser());

        borrow.setBook(book);

        borrow.setIssueDate(LocalDate.now());

        borrow.setDueDate(
                LocalDate.now().plusDays(15));

        borrow.setStatus(BorrowStatus.ISSUED);

        borrow.setFineAmount(0);



        borrowRepository.save(borrow);



        book.setAvailableQuantity(
                book.getAvailableQuantity()-1);


        bookRepository.save(book);



        reservation.setStatus(
                ReservationStatus.APPROVED);



        Reservation updated =
                reservationRepository.save(reservation);



        return convertToDto(updated);

    }







    @Override
    public ReservationDto rejectReservation(Integer reservationId) {


        Reservation reservation =
                reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                new ReservationNotFoundException(
                        "Reservation Not Found"));



        reservation.setStatus(
                ReservationStatus.REJECTED);



        return convertToDto(
                reservationRepository.save(reservation));

    }







    @Override
    public List<ReservationDto> getPendingReservations() {


        return convertList(
                reservationRepository.findByStatus(
                ReservationStatus.PENDING));

    }






    @Override
    public List<ReservationDto> getAllReservations() {


        return convertList(
                reservationRepository.findAll());

    }






    @Override
    public List<ReservationDto> getReservationsByUser(Integer userId) {


        return convertList(
                reservationRepository
                .findByUserUserId(userId));

    }






    @Override
    public ReservationDto getReservationById(Integer reservationId) {


        Reservation reservation =
                reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                new ReservationNotFoundException(
                        "Reservation Not Found"));


        return convertToDto(reservation);

    }







    @Override
    public void cancelReservation(Integer reservationId) {


        Reservation reservation =
                reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                new ReservationNotFoundException(
                        "Reservation Not Found"));



        if(reservation.getStatus()
                !=ReservationStatus.PENDING){

            throw new RuntimeException(
                    "Only pending request can cancel");

        }



        reservationRepository.delete(reservation);

    }






    private ReservationDto convertToDto(
            Reservation reservation){


        ReservationDto dto =
                new ReservationDto();


        dto.setReservationId(
                reservation.getReservationId());


        dto.setReservationDate(
                reservation.getReservationDate());


        dto.setStatus(
                reservation.getStatus());



        dto.setUserId(
                reservation.getUser().getUserId());


        dto.setBookId(
                reservation.getBook().getBookId());


        return dto;

    }





    private List<ReservationDto> convertList(
            List<Reservation> reservations){


        List<ReservationDto> list =
                new ArrayList<>();


        for(Reservation r:reservations){

            list.add(convertToDto(r));

        }


        return list;

    }

}