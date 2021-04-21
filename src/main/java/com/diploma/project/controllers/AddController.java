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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


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
                          @RequestParam String characteristics, @RequestParam String price, Model model) throws IOException {
        if(name!=null && devModel!=null && file!=null && characteristics!=null && price!=null){
            File fPath = new File(uploadPath);
            if(!fPath.exists()){
                fPath.mkdir();
            }
            Device dev = new Device(name,devModel,characteristics,price);

            //file.transferTo(new File(resFileName));
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath + file.getOriginalFilename());
            Files.write(path, bytes);
            dev.setImage("images/"+ file.getOriginalFilename());
            deviceRepo.save(dev);
        }
        return "add_product";
    }
}
