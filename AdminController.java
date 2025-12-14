package com.healthhubutm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private boolean isAdmin(HttpSession session) {
        return session != null && "admin".equals(session.getAttribute("role"));
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }
        return "admin/dashboard";
    }

    @GetMapping("/programs")
    public String programList(HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }
        return "admin/program-list";
    }

    @GetMapping("/program/create")
    public String createProgramForm(HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }
        return "admin/program-form";
    }

    @PostMapping("/program/create")
    public String saveProgram(
            @RequestParam String name,
            HttpSession session
    ) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }
        return "redirect:/admin/programs";
    }
}
