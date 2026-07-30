package com.lms.service;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BookDto;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.exception.UserNotFoundException;

public interface IBookService {
	
	public ResponseEntity<BookDto> saveBook(BookDto bookDto,int libraryId) throws LibraryNotFoundException;
	public ResponseEntity<BookDto>findById(int bookId) throws BookNotFoundException;

}
