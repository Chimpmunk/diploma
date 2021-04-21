package com.diploma.project.controllers;

import com.diploma.project.models.Device;
import com.diploma.project.repo.DeviceRepo;
import com.diploma.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class DeviceController {
    @Autowired
    private DeviceRepo repo;

    @GetMapping("/device/{id}")
    public String devicePage(Model model, @PathVariable(value="id") long id){
        Device d = repo.findOneById(id);
        model.addAttribute("d", d);
        return "device";
    }
}
