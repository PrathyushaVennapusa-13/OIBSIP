package com.lms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lms.Iservice.IAdminService;
import com.lms.dto.AdminDto;
import com.lms.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String loginAdmin(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session){

        AdminDto admin = adminService.adminLogin(email, password);
        session.setAttribute("admin", admin);
        model.addAttribute("admin", admin);

        return "admin/dashboard";
    }


    @GetMapping("/users")
    public String getAllUsers(Model model) {

        List<UserDto> users = adminService.getAllUsers();

        model.addAttribute("users", users);

        return "admin/users";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("admin", new AdminDto());

        return "admin/dashboard";
    }

}