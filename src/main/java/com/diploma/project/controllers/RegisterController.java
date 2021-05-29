package com.diploma.project.controllers;



import com.diploma.project.models.User;
import com.diploma.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String reg(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String regPost(@RequestParam String username, @RequestParam String firstName, @RequestParam String password,
                          @RequestParam String lastName, @RequestParam String email, Model model) {

        User usr= new User(username,firstName,lastName,email,passwordEncode().encode(password),"USER");
        usr.setActive(true);
        userRepo.save(usr);
        model.addAttribute("txt", "Вы зарегистрированы");
        return "registration";
    }

    @Bean
    PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }
}
