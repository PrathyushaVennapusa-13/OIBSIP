package com.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lms.dto.BorrowDto;
import com.lms.serviceimpl.BorrowService;

@Controller
@RequestMapping("/borrow")
public class BorrowContoller {
	
	private final BorrowService borrowService;
	public  BorrowContoller(BorrowService borrowService)
	{
		this.borrowService =borrowService;
	}

	@PostMapping("/{userId}/{bookId}")
	public ResponseEntity<String> saveBorrow(@PathVariable  int userId, @PathVariable int bookId) 
	
	{
		return borrowService.saveBorrow( userId, bookId);
	}
	@PostMapping("/{borrowId}")
	public ResponseEntity<String> returnBorrow(@PathVariable int borrowId) 
	{
		return borrowService.returnBorrow(borrowId);
	}
}
