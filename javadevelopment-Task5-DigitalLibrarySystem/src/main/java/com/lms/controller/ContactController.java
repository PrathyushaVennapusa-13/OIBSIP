package com.lms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lms.Iservice.IContactService;
import com.lms.dto.ContactDto;
import com.lms.dto.UserDto;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/contact")
public class ContactController {


    private final IContactService contactService;



    public ContactController(
            IContactService contactService) {

        this.contactService = contactService;

    }







    // =====================================
    // USER CONTACT PAGE
    // =====================================

    @GetMapping
    public String contactPage(
            Model model,
            HttpSession session) {



        UserDto user =
                (UserDto) session
                .getAttribute("user");



        if(user == null) {

            return "redirect:/login";

        }





        ContactDto contact =
                new ContactDto();



        contact.setUserId(
                user.getUserId());



        model.addAttribute(
                "contact",
                contact);



        model.addAttribute(
                "user",
                user);



        return "user/contact";

    }








    // =====================================
    // USER SEND MESSAGE
    // =====================================

    @PostMapping("/send")
    public String sendMessage(
            @ModelAttribute("contact") ContactDto contactDto,
            HttpSession session) {


        UserDto user =
                (UserDto) session.getAttribute("user");


        if(user == null) {

            return "redirect:/login";

        }


        // Set logged-in user id
        contactDto.setUserId(
                user.getUserId()
        );


        contactService.sendMessage(contactDto);


        return "redirect:/contact";

    }


    // =====================================
    // ADMIN VIEW ALL MESSAGES
    // =====================================

    @GetMapping("/all")
    public String getAllMessages(
            Model model) {



        List<ContactDto> contacts =
                contactService
                .getAllMessages();



        model.addAttribute(
                "contacts",
                contacts);



        return "admin/contacts";

    }








    // =====================================
    // ADMIN VIEW MESSAGE
    // =====================================

    @GetMapping("/{contactId}")
    public String getMessageById(
            @PathVariable Integer contactId,
            Model model) {



        ContactDto contact =
                contactService
                .getMessageById(contactId);



        model.addAttribute(
                "contact",
                contact);



        return "admin/contactDetails";

    }








    // =====================================
    // ADMIN DELETE MESSAGE
    // =====================================

    @PostMapping("/delete/{contactId}")
    public String deleteMessage(
            @PathVariable Integer contactId) {


        contactService.deleteMessage(contactId);


        return "redirect:/contact/all";

    }


}