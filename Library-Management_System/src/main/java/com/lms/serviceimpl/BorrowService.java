package com.lms.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.lms.entity.Book;
import com.lms.entity.Borrow;
import com.lms.entity.User;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.BorrowNotFoundException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.BorrowRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IBorrowService;

@Service
public class BorrowService implements IBorrowService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	private final BorrowRepository borrowRepository;
	
	public BorrowService( ModelMapper modelMapper,UserRepository userRepository,BookRepository bookRepository,BorrowRepository borrowRepository)
	{
		this.bookRepository=bookRepository;
		this.modelMapper=modelMapper;
		this.userRepository=userRepository;
		this.borrowRepository=borrowRepository;
	}
	
	
	
	@Override
	public ResponseEntity<String> saveBorrow( int userId, int bookId) {
		Borrow borrow =new Borrow();
		Optional<User> optionalUser=userRepository.findById(userId);
		Optional<Book> optionalBook =bookRepository.findById(bookId);
		if(optionalUser.isPresent() && optionalBook .isPresent())
		{
			Book book =optionalBook.get();
			User user =optionalUser.get();
			if(book.getAvailableCopies()>=1)
			{
			List<Borrow>userBorrows =user.getBorrows();
			if(userBorrows==null)
			{
				userBorrows =new ArrayList<>();
			}
			userBorrows.add(borrow);
			user.setBorrows(userBorrows);
			
			
			List<Borrow>bookBorrows =book.getBorrows();
			if(bookBorrows==null)
			{
				bookBorrows =new ArrayList<>();
			}
			bookBorrows.add(borrow);
			book.setBorrows(bookBorrows);
			
			
			borrow.setIssueDate(LocalDateTime.now());
			borrow.setBook(book);
			book.setAvailableCopies(book.getNoOfCopy()-1);
			book.setIssuedCopies(book.getIssuedCopies()+1);
			borrow.setUser(user);
			borrowRepository.save(borrow);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Borrow successfull to "+user.getUserName());
		
		}
		 
		
		else if(optionalUser.isEmpty())
		{
			throw new UserNotFoundException("user  details not found");
		}
		else
		{
			throw new BookNotFoundException("book not found exception");
		}
	}



	@Override
	public ResponseEntity<String> returnBorrow( int borrowId) {
		
		Optional<Borrow> optionalBorrow =borrowRepository.findById(borrowId);
		
		if(optionalBorrow.isPresent())
		{
		Borrow borrow =optionalBorrow.get();
		borrow.setReturnDate(LocalDateTime.now());
		Book book =borrow.getBook();
		book.setIssuedCopies(book.getIssuedCopies()-1);
		book.setAvailableCopies(book.getAvailableCopies()-1);
		bookRepository.save(book);
		borrowRepository.save(borrow);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Book Returned Successfully");
		}
		
	throw new BorrowNotFoundException("Borrow Not found");
	}
	

}
