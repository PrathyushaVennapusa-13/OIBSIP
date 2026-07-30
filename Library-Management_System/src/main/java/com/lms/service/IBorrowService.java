package com.lms.service;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BorrowDto;

public interface IBorrowService {
	
	
	public ResponseEntity<String> saveBorrow(int userId,int bookId);
	public ResponseEntity<String>returnBorrow(int borrowId);

}
