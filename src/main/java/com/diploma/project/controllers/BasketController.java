package com.diploma.project.controllers;

import com.diploma.project.models.Basket;
import com.diploma.project.models.Device;
import com.diploma.project.models.UserPrincipal;
import com.diploma.project.repo.BasketRepo;
import com.diploma.project.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class BasketController {
    @Autowired
    private DeviceRepo repo;
    @Autowired
    private BasketRepo basketRepo;

    @GetMapping("/device/add-to-basket/{id}")
    public String addToBasket(@AuthenticationPrincipal UserPrincipal user, @PathVariable(value = "id") long id, Model model){
        Device device = repo.findOneById(id);
        Basket basket = basketRepo.findByUser(user.getUser());
        if(basket==null){
            basket = new Basket();
            basket.setUser(user.getUser());
            Set<Device> items = new HashSet<Device>();
            items.add(device);
            basket.setItems(items);
        } else{
            Set<Device> items = basket.getItems();

                items.add(device);
                basket.setItems(items);

        }
        basketRepo.save(basket);
        model.addAttribute("d",device);
        return "redirect:/device/"+id;
    }

    @GetMapping("/basket")
    public String getBasket(@AuthenticationPrincipal UserPrincipal user, Model model){
        Basket basket = basketRepo.findByUser(user.getUser());
        if(basket!=null){
            model.addAttribute("items",basket.getItems());
        }
        return "basket";
    }

    @GetMapping("/basket-clear")
    public String clearBasket(@AuthenticationPrincipal UserPrincipal user, Model model){
        /*Basket basket= basketRepo.findByUser(user.getUser());
        basketRepo.delete(basket);
        basket= new Basket();
        basket.setUser(user.getUser());
        basketRepo.save(basket);*/
        Basket basket= basketRepo.findByUser(user.getUser());
        basket.setItems(new HashSet<Device>());
        basketRepo.save(basket);
        return "redirect:/basket";
    }
}
