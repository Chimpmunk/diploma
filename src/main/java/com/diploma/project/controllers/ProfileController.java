package com.diploma.project.controllers;

import com.diploma.project.models.User;
import com.diploma.project.models.UserPrincipal;
import com.diploma.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @Autowired
    private UserRepo repo;
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserPrincipal user, Model model){
        model.addAttribute("user", user.getUser());
        return "profile";
    }
}
