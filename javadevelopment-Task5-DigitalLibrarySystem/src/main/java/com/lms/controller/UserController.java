package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lms.Iservice.IBookService;
import com.lms.Iservice.IUserService;
import com.lms.dto.UserDto;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {


    private final IUserService userService;
    private final IBookService bookService;


    public UserController(IUserService userService,
                          IBookService bookService) {

        this.userService = userService;
        this.bookService = bookService;

    }



    // ==========================
    // USER DASHBOARD
    // ==========================

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {


        UserDto user =
                (UserDto) session.getAttribute("user");


        if(user == null) {

            return "redirect:/user/login";
        }


        model.addAttribute("user", user);


        return "user/dashboard";
    }






    // ==========================
    // BOOKS
    // ==========================

    @GetMapping("/books")
    public String browseBooks(
            Model model,
            HttpSession session) {


        UserDto user =
                (UserDto) session.getAttribute("user");


        if(user == null){

            return "redirect:/user/login";

        }


        model.addAttribute(
                "books",
                bookService.getAllBooks()
        );


        model.addAttribute(
                "user",
                user
        );


        return "user/books";
    }

    // ==========================
    // REGISTER
    // ==========================

    @GetMapping("/register")
    public String registerPage(Model model) {


        model.addAttribute("user",
                new UserDto());


        return "register";

    }




    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") UserDto userDto) {


        userService.registerUser(userDto);


        return "redirect:/user/login";

    }







    // ==========================
    // LOGIN PAGE
    // ==========================

    @GetMapping("/login")
    public String loginPage() {


        return "login";

    }






    // ==========================
    // LOGIN USER
    // ==========================

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {


        UserDto user =
                userService.loginUser(email, password);



        if(user == null) {


            model.addAttribute(
                    "error",
                    "Invalid email or password"
            );


            return "login";

        }



        session.setAttribute(
                "user",
                user
        );



        return "redirect:/user/dashboard";

    }








    // ==========================
    // PROFILE
    // ==========================

    @GetMapping("/profile/{userId}")
    public String getProfile(
            @PathVariable Integer userId,
            Model model,
            HttpSession session) {



        UserDto user =
                userService.getUserById(userId);



        model.addAttribute(
                "user",
                user
        );



        session.setAttribute(
                "user",
                user
        );



        return "user/profile";

    }






    // ==========================
    // UPDATE NAME
    // ==========================

    @PostMapping("/updateName/{userId}")
    public String updateName(
            @PathVariable Integer userId,
            @RequestParam String userName) {


        userService.updateUserName(
                userId,
                userName
        );


        return "redirect:/user/profile/" + userId;

    }






    // ==========================
    // UPDATE EMAIL
    // ==========================

    @PostMapping("/updateEmail/{userId}")
    public String updateEmail(
            @PathVariable Integer userId,
            @RequestParam String email) {


        userService.updateUserEmail(
                userId,
                email
        );


        return "redirect:/user/profile/" + userId;

    }







    // ==========================
    // UPDATE PHONE
    // ==========================

    @PostMapping("/updatePhone/{userId}")
    public String updatePhone(
            @PathVariable Integer userId,
            @RequestParam Long phoneNumber) {


        userService.updateUserPhoneNumber(
                userId,
                phoneNumber
        );


        return "redirect:/user/profile/" + userId;

    }







    // ==========================
    // UPDATE PASSWORD
    // ==========================

    @PostMapping("/updatePassword/{userId}")
    public String updatePassword(
            @PathVariable Integer userId,
            @RequestParam String password) {


        userService.updateUserPassword(
                userId,
                password
        );


        return "redirect:/user/profile/" + userId;

    }







    // ==========================
    // LOGOUT
    // ==========================

    @GetMapping("/logout")
    public String logout(HttpSession session) {


        session.invalidate();


        return "redirect:/user/login";

    }



}