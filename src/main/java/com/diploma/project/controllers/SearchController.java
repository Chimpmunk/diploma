package com.diploma.project.controllers;

import com.diploma.project.models.Device;
import com.diploma.project.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SearchController {
    @Autowired
    private DeviceRepo repo;

    @PostMapping("/search")
    public String search( Model model, @RequestParam String req){
        String[] requests = req.split(" ");
        Set<Device> devices = new HashSet<Device>();
        for (String r: requests){
            devices.addAll(repo.findByNameContaining(r));
            devices.addAll(repo.findByDevModelContaining(r));
            devices.addAll(repo.findByCharacteristics(r));
        }
        model.addAttribute("devices", devices);
        return "search";
    }
}
