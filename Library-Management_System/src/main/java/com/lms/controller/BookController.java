package com.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.BookDto;
import com.lms.service.IBookService;

@RestController
@RequestMapping("/book")
public class BookController {

	final IBookService bookService;
	BookController(IBookService bookService)
	{
		this.bookService=bookService;
	}
	
	@PostMapping("/{libraryId}")
	public ResponseEntity<BookDto> saveBook(@RequestBody   BookDto bookDto, @PathVariable int libraryId) {

		return bookService.saveBook(bookDto,libraryId);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto>findById( @PathVariable   int bookId)
	{
		return bookService.findById(bookId);
	}
}
