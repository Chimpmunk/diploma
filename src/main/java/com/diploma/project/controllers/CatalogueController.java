package com.diploma.project.controllers;

import com.diploma.project.models.Category;
import com.diploma.project.models.Device;
import com.diploma.project.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CatalogueController {
    @Autowired
    private DeviceRepo repo;

    @GetMapping("/catalogue/{category}")
    public String getCatalogue(@PathVariable(value="category") String cat, Model model){
        Category category;
        switch (cat){
            case "smartphone":
                category = Category.SMARTPHONE;
                break;
            case "laptop":
                category = Category.LAPTOP;
                break;
            case "tablet":
                category=Category.TABLET;
                break;
            default:
                category = Category.SMARTPHONE;
                break;
        }
        Iterable<Device> devices =repo.findByCategory(category);
        model.addAttribute("devices", devices);
        return "catalogue";
    }

    @PostMapping("/search")
    public String search( Model model, @RequestParam String req){
        String[] requests = req.split(" ");
        Set<Device> devices = new HashSet<Device>();
        for (String r: requests){
            devices.addAll(repo.findByNameContaining(r));
            devices.addAll(repo.findByDevModelContaining(r));
            devices.addAll(repo.findByCharacteristicsContaining(r));
        }
        model.addAttribute("devices", devices);
        return "catalogue";
    }

}
