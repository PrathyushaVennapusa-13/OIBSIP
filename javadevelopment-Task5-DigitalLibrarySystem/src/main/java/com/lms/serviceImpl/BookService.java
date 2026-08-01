package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.Iservice.IBookService;
import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.exception.BookAlreadyExistsException;
import com.lms.exception.BookNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.util.BookCategory;


@Service
public class BookService implements IBookService {


    private final BookRepository bookRepository;



    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;

    }






    // ===============================
    // ADD BOOK
    // ===============================

    @Override
    public BookDto addBook(BookDto bookDto) {


        Optional<Book> optional =
                bookRepository.findByIsbn(
                        bookDto.getIsbn());



        if(optional.isPresent()){

            throw new BookAlreadyExistsException(
                    "Book Already Exists");

        }



        Book book = new Book();


        book.setTitle(
                bookDto.getTitle());


        book.setAuthor(
                bookDto.getAuthor());


        book.setIsbn(
                bookDto.getIsbn());


        book.setCategory(
                bookDto.getCategory());


        book.setTotalQuantity(
                bookDto.getTotalQuantity());


        book.setAvailableQuantity(
                bookDto.getTotalQuantity());



        Book saved =
                bookRepository.save(book);



        return convertToDto(saved);

    }








    // ===============================
    // GET BOOK BY ID
    // ===============================

    @Override
    public BookDto getBookById(Integer bookId) {


        Book book =
                bookRepository.findById(bookId)
                .orElseThrow(() ->
                new BookNotFoundException(
                        "Book Not Found"));



        return convertToDto(book);

    }








    // ===============================
    // GET ALL BOOKS
    // ===============================

    @Override
    public List<BookDto> getAllBooks() {


        return convertList(
                bookRepository.findAll());

    }








    // ===============================
    // SEARCH TITLE
    // ===============================

    @Override
    public List<BookDto> searchBookByTitle(String title) {


        return convertList(
                bookRepository
                .findByTitleContainingIgnoreCase(title));

    }








    // ===============================
    // SEARCH AUTHOR
    // ===============================

    @Override
    public List<BookDto> searchBookByAuthor(String author) {


        return convertList(
                bookRepository
                .findByAuthorContainingIgnoreCase(author));

    }








    // ===============================
    // SEARCH CATEGORY
    // ===============================

    @Override
    public List<BookDto> searchBookByCategory(BookCategory category) {


        return convertList(
                bookRepository
                .findByCategory(category));

    }








    // ===============================
    // UPDATE TITLE
    // ===============================

    @Override
    public BookDto updateBookTitle(
            Integer bookId,
            String title) {


        Book book =
                getBook(bookId);


        book.setTitle(title);


        return convertToDto(
                bookRepository.save(book));

    }








    // ===============================
    // UPDATE AUTHOR
    // ===============================

    @Override
    public BookDto updateBookAuthor(
            Integer bookId,
            String author) {


        Book book =
                getBook(bookId);



        book.setAuthor(author);



        return convertToDto(
                bookRepository.save(book));

    }








    // ===============================
    // UPDATE ISBN
    // ===============================

    @Override
    public BookDto updateBookISBN(
            Integer bookId,
            String isbn) {


        Book book =
                getBook(bookId);



        Optional<Book> existing =
                bookRepository.findByIsbn(isbn);



        if(existing.isPresent()
                &&
                !existing.get()
                .getBookId()
                .equals(bookId)) {


            throw new BookAlreadyExistsException(
                    "ISBN Already Exists");

        }



        book.setIsbn(isbn);



        return convertToDto(
                bookRepository.save(book));

    }








    // ===============================
    // UPDATE CATEGORY
    // ===============================

    @Override
    public BookDto updateBookCategory(
            Integer bookId,
            BookCategory category) {


        Book book =
                getBook(bookId);



        book.setCategory(category);



        return convertToDto(
                bookRepository.save(book));

    }








    // ===============================
    // ADD STOCK
    // ===============================

    @Override
    public BookDto addBookStock(
            Integer bookId,
            Integer quantity) {


        Book book =
                getBook(bookId);



        book.setTotalQuantity(
                book.getTotalQuantity()
                + quantity);



        book.setAvailableQuantity(
                book.getAvailableQuantity()
                + quantity);



        return convertToDto(
                bookRepository.save(book));

    }








    // ===============================
    // DELETE BOOK
    // ===============================

    @Override
    public void deleteBook(Integer bookId) {


        Book book =
                getBook(bookId);



        bookRepository.delete(book);

    }








    private Book getBook(Integer bookId){


        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                new BookNotFoundException(
                        "Book Not Found"));

    }








    // ===============================
    // ENTITY TO DTO
    // ===============================

    private BookDto convertToDto(Book book){


        BookDto dto =
                new BookDto();


        dto.setBookId(
                book.getBookId());


        dto.setTitle(
                book.getTitle());


        dto.setAuthor(
                book.getAuthor());


        dto.setIsbn(
                book.getIsbn());


        dto.setCategory(
                book.getCategory());


        dto.setTotalQuantity(
                book.getTotalQuantity());


        dto.setAvailableQuantity(
                book.getAvailableQuantity());



        return dto;

    }








    private List<BookDto> convertList(
            List<Book> books){


        List<BookDto> list =
                new ArrayList<>();



        for(Book book:books){

            list.add(
                    convertToDto(book));

        }


        return list;

    }


}