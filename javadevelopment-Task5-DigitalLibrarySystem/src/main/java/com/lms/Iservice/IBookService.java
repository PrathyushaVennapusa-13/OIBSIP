package com.lms.Iservice;

import java.util.List;

import com.lms.dto.BookDto;
import com.lms.util.BookCategory;

public interface IBookService {

	BookDto addBook(BookDto bookDto);

	BookDto getBookById(Integer bookId);

	List<BookDto> getAllBooks();

	List<BookDto> searchBookByTitle(String title);

	List<BookDto> searchBookByAuthor(String author);

	List<BookDto> searchBookByCategory(BookCategory category);

	BookDto updateBookTitle(Integer bookId, String title);

	BookDto updateBookAuthor(Integer bookId, String author);

	BookDto updateBookISBN(Integer bookId, String isbn);

	BookDto updateBookCategory(Integer bookId, BookCategory category);

	BookDto addBookStock(Integer bookId, Integer quantity);

	void deleteBook(Integer bookId);
}
