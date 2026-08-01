package com.lms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lms.Iservice.IBookService;
import com.lms.dto.BookDto;
import com.lms.dto.UserDto;
import com.lms.util.BookCategory;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }


    // ==========================
    // ADMIN ONLY - OPEN ADD BOOK
    // ==========================

    @GetMapping("/add")
    public String addBookPage(Model model,
                              HttpSession session) {


        if(session.getAttribute("admin") == null) {

            return "redirect:/admin/login";
        }


        model.addAttribute("book", new BookDto());

        return "admin/add-book";
    }



    // ==========================
    // ADMIN ONLY - ADD BOOK
    // ==========================

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDto bookDto,
                          HttpSession session) {


        if(session.getAttribute("admin") == null) {

            return "redirect:/admin/login";
        }


        bookService.addBook(bookDto);


        return "redirect:/book/all";
    }



    // ==========================
    // VIEW BOOKS
    // ADMIN -> admin/books
    // USER  -> user/books
    // ==========================

    @GetMapping("/all")
    public String getAllBooks(Model model,
                              HttpSession session) {


        List<BookDto> books = bookService.getAllBooks();

        model.addAttribute("books", books);



        if(session.getAttribute("admin") != null) {

            return "admin/books";

        }


        if(session.getAttribute("user") != null) {

            UserDto user = (UserDto) session.getAttribute("user");

            model.addAttribute("userId", user.getUserId());

        }


        return "user/books";
    }

    // ==========================
    // SEARCH TITLE
    // ==========================

    @GetMapping("/search/title")
    public String searchByTitle(@RequestParam String title,
                                Model model,
                                HttpSession session) {


        List<BookDto> books =
                bookService.searchBookByTitle(title);


        model.addAttribute("books", books);


        if(session.getAttribute("admin") != null) {

            return "admin/books";

        }


        if(session.getAttribute("user") != null) {

            UserDto user = (UserDto) session.getAttribute("user");

            model.addAttribute("userId", user.getUserId());

        }


        return "user/books";
    }



    // ==========================
    // SEARCH AUTHOR
    // ==========================

    @GetMapping("/search/author")
    public String searchByAuthor(@RequestParam String author,
                                 Model model,
                                 HttpSession session) {


        List<BookDto> books =
                bookService.searchBookByAuthor(author);


        model.addAttribute("books", books);


        if(session.getAttribute("admin") != null) {

            return "admin/books";

        }


        if(session.getAttribute("user") != null) {

            UserDto user = (UserDto) session.getAttribute("user");

            model.addAttribute("userId", user.getUserId());

        }


        return "user/books";
    }



    // ==========================
    // SEARCH CATEGORY
    // ==========================

    @GetMapping("/search/category")
    public String searchByCategory(@RequestParam BookCategory category,
                                   Model model,
                                   HttpSession session) {


        List<BookDto> books =
                bookService.searchBookByCategory(category);


        model.addAttribute("books", books);


        if(session.getAttribute("admin") != null) {

            return "admin/books";

        }


        if(session.getAttribute("user") != null) {

            UserDto user = (UserDto) session.getAttribute("user");

            model.addAttribute("userId", user.getUserId());

        }


        return "user/books";
    }



    // ==========================
    // ADMIN ONLY UPDATE TITLE
    // ==========================

    @PostMapping("/update/title/{bookId}")
    public String updateTitle(@PathVariable Integer bookId,
                              @RequestParam String title,
                              HttpSession session) {


        if(session.getAttribute("admin")==null){

            return "redirect:/admin/login";
        }


        bookService.updateBookTitle(bookId, title);


        return "redirect:/book/all";
    }




    // ==========================
    // ADMIN ONLY UPDATE AUTHOR
    // ==========================

    @PostMapping("/update/author/{bookId}")
    public String updateAuthor(@PathVariable Integer bookId,
                               @RequestParam String author,
                               HttpSession session) {


        if(session.getAttribute("admin")==null){

            return "redirect:/admin/login";
        }


        bookService.updateBookAuthor(bookId, author);


        return "redirect:/book/all";
    }





    // ==========================
    // ADMIN ONLY UPDATE ISBN
    // ==========================

    @PostMapping("/update/isbn/{bookId}")
    public String updateISBN(@PathVariable Integer bookId,
                             @RequestParam String isbn,
                             HttpSession session) {


        if(session.getAttribute("admin")==null){

            return "redirect:/admin/login";
        }


        bookService.updateBookISBN(bookId, isbn);


        return "redirect:/book/all";
    }





    // ==========================
    // ADMIN ONLY UPDATE CATEGORY
    // ==========================

    @PostMapping("/update/category/{bookId}")
    public String updateCategory(@PathVariable Integer bookId,
                                 @RequestParam BookCategory category,
                                 HttpSession session) {


        if(session.getAttribute("admin")==null){

            return "redirect:/admin/login";
        }


        bookService.updateBookCategory(bookId, category);


        return "redirect:/book/all";
    }





    // ==========================
    // ADMIN ONLY ADD STOCK
    // ==========================

    @PostMapping("/stock/{bookId}")
    public String addBookStock(@PathVariable Integer bookId,
                               @RequestParam Integer quantity,
                               HttpSession session) {


        if(session.getAttribute("admin")==null){

            return "redirect:/admin/login";
        }


        bookService.addBookStock(bookId, quantity);


        return "redirect:/book/all";
    }





    // ==========================
    // ADMIN ONLY DELETE BOOK
    // ==========================

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Integer bookId,
                             HttpSession session) {


        if(session.getAttribute("admin")==null){

            return "redirect:/admin/login";
        }


        bookService.deleteBook(bookId);


        return "redirect:/book/all";
    }

}