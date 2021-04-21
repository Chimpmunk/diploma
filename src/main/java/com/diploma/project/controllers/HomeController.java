package com.diploma.project.controllers;

import com.diploma.project.models.Device;
import com.diploma.project.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private DeviceRepo deviceRepo;

    @GetMapping("/")
    public String home(Model model) {

        Iterable<Device> devices = deviceRepo.findAll();

        model.addAttribute("devices", devices);
        return "home";
    }

}
