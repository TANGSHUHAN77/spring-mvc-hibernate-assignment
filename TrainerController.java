package com.healthhubutm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private boolean isMember(HttpSession session) {
        return session != null && "member".equals(session.getAttribute("role"));
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (!isMember(session)) {
            return "redirect:/auth/login";
        }
        return "member/dashboard";
    }

    @GetMapping("/bmi")
    public String bmiForm(HttpSession session) {
        if (!isMember(session)) {
            return "redirect:/auth/login";
        }
        return "member/bmi-form";
    }

    @PostMapping("/bmi")
    public String calculateBmi(
            @RequestParam double height,
            @RequestParam double weight,
            HttpSession session,
            Model model
    ) {
        if (!isMember(session)) {
            return "redirect:/auth/login";
        }

        double bmi = weight / (height * height);
        model.addAttribute("bmi", bmi);
        return "member/bmi-result";
    }

    @GetMapping("/programs")
    public String viewPrograms(HttpSession session) {
        if (!isMember(session)) {
            return "redirect:/auth/login";
        }
        return "member/program-list";
    }
}
