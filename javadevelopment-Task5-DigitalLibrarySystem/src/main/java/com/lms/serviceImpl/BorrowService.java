package com.lms.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.Iservice.IBorrowService;
import com.lms.dto.BorrowDto;
import com.lms.entity.Book;
import com.lms.entity.Borrow;
import com.lms.exception.BorrowNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.BorrowRepository;
import com.lms.util.BorrowStatus;


@Service
public class BorrowService implements IBorrowService {


    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;



    public BorrowService(BorrowRepository borrowRepository,BookRepository bookRepository) {

        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;

    }


    @Override
    public BorrowDto getBorrowById(Integer borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId) .orElseThrow(() -> new BorrowNotFoundException("Borrow Not Found"));
        return convertToDto(borrow);

    }

    @Override
    public List<BorrowDto> getAllBorrows() {
        return convertList( borrowRepository.findAll());
    }


    @Override
    public List<BorrowDto> getBorrowsByUser(Integer userId) {
        return convertList(borrowRepository.findByUserUserId(userId));
    }


    @Override
    public BorrowDto requestReturn(Integer borrowId) {
        Borrow borrow =borrowRepository.findById(borrowId).orElseThrow(() -> new BorrowNotFoundException("Borrow Not Found"));
        if(borrow.getStatus() != BorrowStatus.ISSUED) {
            throw new RuntimeException("Return request already submitted");
        }
        borrow.setStatus(BorrowStatus.RETURN_REQUESTED);
        return convertToDto(borrowRepository.save(borrow));

    }

    @Override
    public BorrowDto approveReturn(Integer borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(() ->new BorrowNotFoundException("Borrow Not Found"));
        if(borrow.getStatus()!= BorrowStatus.RETURN_REQUESTED){
            throw new RuntimeException("Return request not found");
        }

        LocalDate today = LocalDate.now();
        borrow.setReturnDate(today);
        double fine =calculateFine(borrowId);
        borrow.setFineAmount(fine);
        borrow.setStatus(BorrowStatus.RETURNED);
        Book book =borrow.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity()+1);
        bookRepository.save(book);
        return convertToDto(borrowRepository.save(borrow));

    }


    @Override
    public double calculateFine(Integer borrowId) {
        Borrow borrow =borrowRepository.findById(borrowId).orElseThrow(() ->new BorrowNotFoundException( "Borrow Not Found"));
        if(borrow.getDueDate()==null || borrow.getReturnDate()==null){
        return 0;
        }

        if(borrow.getReturnDate().isAfter(borrow.getDueDate())){
            long days = ChronoUnit.DAYS.between( borrow.getDueDate(), borrow.getReturnDate());
            return days * 5;
        }
        return 0;

    }



 private BorrowDto convertToDto(Borrow borrow){
     BorrowDto dto = new BorrowDto();
     dto.setBorrowId(borrow.getBorrowId());
     dto.setIssueDate(borrow.getIssueDate());
     dto.setDueDate(borrow.getDueDate());
     dto.setReturnDate(borrow.getReturnDate());
     dto.setStatus(borrow.getStatus());
     dto.setFineAmount(borrow.getFineAmount());
     if(borrow.getUser() != null){
        dto.setUserId(borrow.getUser().getUserId());
     }
     if(borrow.getBook() != null){
         dto.setBookId(borrow.getBook().getBookId());
         dto.setBookTitle(borrow.getBook().getTitle());
     }
     return dto;

 }

    private List<BorrowDto> convertList(List<Borrow> borrows){


        List<BorrowDto> list =new ArrayList<>();
        for(Borrow borrow:borrows){
        list.add(convertToDto(borrow));
        }
        return list;

    }
}
