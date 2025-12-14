package com.healthhubutm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            HttpSession session,
            Model model
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Username and password are required");
            return "login";
        }

        session.setAttribute("username", username);
        session.setAttribute("role", role);

        switch (role) {
            case "member":
                return "redirect:/member/dashboard";
            case "trainer":
                return "redirect:/trainer/dashboard";
            case "admin":
                return "redirect:/admin/dashboard";
            default:
                model.addAttribute("error", "Invalid role");
                return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
