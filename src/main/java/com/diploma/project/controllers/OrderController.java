package com.diploma.project.controllers;

import com.diploma.project.models.*;
import com.diploma.project.repo.BasketRepo;
import com.diploma.project.repo.ClientOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController {
    @Autowired
    private BasketRepo basketRepo;
    @Autowired
    private ClientOrderRepo clientOrderRepo;

    @PostMapping("/order")
    public String getOrder(@AuthenticationPrincipal UserPrincipal user, @RequestParam List<Integer> quantity, Model model){
        Basket b = basketRepo.findByUser(user.getUser());
        /*List<Device> orderList = new LinkedList<Device>();
        for (int i=0;i<quantity.size();i++){
            Device device = b.getItems().get(i);
            for(int j=0;j<quantity.get(i);j++){
                orderList.add(device);
            }
        }
        ClientOrder order = new ClientOrder(user.getUser(),orderList);
        orderRepo.save(order);
        model.addAttribute("items", b.getItems());
        basketRepo.delete(b);*/
        Set<Device> devs = b.getItems();
        Set<Devices> orderSet = new HashSet<Devices>();
        int i =devs.size()-1;
        for (Device d:devs){
            orderSet.add(new Devices(d,quantity.get(i)));
            i--;
        }
        ClientOrder order = new ClientOrder(user.getUser(),orderSet);
        model.addAttribute("items", order.getItems());
        model.addAttribute("total",order.getTotal());
        clientOrderRepo.save(order);
        basketRepo.delete(b);
        return "order";
    }
}
