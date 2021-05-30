package com.diploma.project.controllers;

import com.diploma.project.models.ClientOrder;
import com.diploma.project.models.User;
import com.diploma.project.models.UserPrincipal;
import com.diploma.project.repo.ClientOrderRepo;
import com.diploma.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private ClientOrderRepo clientOrderRepo;

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserPrincipal user, Model model){
        User u =user.getUser();
        clientOrderRepo.findByUser(u);
        model.addAttribute("user", u);
        return "profile";
    }

    @GetMapping("/profile/my-orders")
    public String getMyOrders(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model){
        List<ClientOrder> orders = clientOrderRepo.findByUser(userPrincipal.getUser());
        model.addAttribute("order",orders);
        return "order";
    }

    @GetMapping("/all-orders")
    public String getAllOrders(Model model){
        List<ClientOrder> orders = clientOrderRepo.findAll();
        model.addAttribute("order",orders);
        return "order";
    }
}
