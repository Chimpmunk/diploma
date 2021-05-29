package com.diploma.project.controllers;

import com.diploma.project.models.Device;
import com.diploma.project.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class AddController {
    @Autowired
    private DeviceRepo deviceRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/add")
    public String add(Model model){
        return "add_product";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String name, @RequestParam String devModel, @RequestParam("file") MultipartFile file,
                          @RequestParam String characteristics, @RequestParam double price, Model model) throws IOException {
        if(name!=null && devModel!=null && file!=null && characteristics!=null){
            Device dev = new Device(name,devModel,characteristics,price);
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String s = uploadPath+"/"+file.getOriginalFilename();
            dev.setImage(file.getOriginalFilename());
            file.transferTo(new File(s));

            deviceRepo.save(dev);
            return "redirect:/device/"+dev.getId();
        }
        return "home";
    }
}
