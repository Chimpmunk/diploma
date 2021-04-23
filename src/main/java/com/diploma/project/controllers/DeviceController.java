package com.diploma.project.controllers;

import com.diploma.project.models.Device;
import com.diploma.project.models.Review;
import com.diploma.project.models.User;
import com.diploma.project.models.UserPrincipal;
import com.diploma.project.repo.DeviceRepo;
import com.diploma.project.repo.ReviewRepo;
import com.diploma.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Controller
public class DeviceController {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private DeviceRepo repo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ReviewRepo reviewRepo;

    @GetMapping("/device/{id}")
    public String devicePage(Model model, @PathVariable(value="id") long id){
        Device d = repo.findOneById(id);
        Iterable<Review> reviews = reviewRepo.findByDevice(d);


        model.addAttribute("d", d);

        model.addAttribute("reviews", reviews);
        return "device";
    }

    @GetMapping("/device/delete/{id}")
    public String deviceDelete(Model model, @PathVariable(value="id") long id){
        Device d = repo.findOneById(id);

        File f = new File(uploadPath+"/"+d.getImage());
        f.delete();
        repo.delete(d);
        return "redirect:/";
    }

    @GetMapping("/device/edit/{id}")
    public String deviceEdit(Model model, @PathVariable(value="id") long id){
        Device d = repo.findOneById(id);

        model.addAttribute("d",d);
        return "edit";
    }

    @PostMapping("/device/edit{id}")
    public String deviceUpdate(@RequestParam String name, @RequestParam String devModel,
    @RequestParam String characteristics, @RequestParam String price, Model model , @PathVariable(value="id") long id){
        Device d = repo.findOneById(id);
        d.setCharacteristics(characteristics);
        d.setName(name);
        d.setDevModel(devModel);
        d.setPrice(price);
        repo.save(d);
        return "redirect:/device/"+id;
    }

    @PostMapping("/device/review/{id}")
    public String addReview(@AuthenticationPrincipal UserPrincipal user, @RequestParam String rating,
                            @RequestParam String comment, @PathVariable(value="id") long id ){
        Device d = repo.findOneById(id);
        Review r = new Review(d,user.getUser(), Double.parseDouble(rating),comment);
        reviewRepo.save(r);
        return "redirect:/device/"+id;
    }
}
