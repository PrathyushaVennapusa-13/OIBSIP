package com.lms.serviceimpl;

import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.BookNotFoundException;
import com.lms.exception.LibraryNotFoundException;

import com.lms.service.IBookService;

@Service
public class BookService implements IBookService {
	
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final LibraryRepository libraryRepository;

	BookService(ModelMapper modelMapper,
			LibraryRepository libraryRepository, BookRepository bookRepository) {
		this.modelMapper = modelMapper;
		this.libraryRepository = libraryRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public ResponseEntity<BookDto> saveBook(BookDto bookDto,int libraryId) {
		
		Book book =modelMapper.map(bookDto, Book.class);
		book.setAvailableCopies(book.getNoOfCopy());
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		if(optionalLibrary.isPresent())
		{
			Library library =optionalLibrary.get();
			List<Book>libraryBooks=library.getBooks();
			if(libraryBooks==null)
			{
				libraryBooks = new ArrayList<>();
				
			}
			libraryBooks.add(book);
			library.setBooks(libraryBooks);
			book.setLibrary(library);
		    bookRepository.save(book);
		    return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
		}
		
		else
		{
		
		throw new LibraryNotFoundException("user Not Found");
		}
	}
	
	public ResponseEntity<BookDto>findById(int bookId)
	{
		Optional<Book>optionalBook =bookRepository.findById(bookId);
		if(optionalBook.isPresent())
		{
			Book book = optionalBook.get();
			BookDto bookDto =modelMapper.map(book, BookDto.class);
			return ResponseEntity.status(HttpStatus.FOUND).body(bookDto);
		}
		else
		{
			throw new BookNotFoundException("Book not found");
		}
	}

}
