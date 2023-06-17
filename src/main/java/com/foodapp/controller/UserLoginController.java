package com.foodapp.controller;

import com.foodapp.entity.User;
import com.foodapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class UserLoginController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "register-form";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user){
        String hshPwd=passwordEncoder.encode(user.getPassword());
        user.setPassword(hshPwd);
        if(user.getRole().equals("USER")) {
            userRepo.save(user);
        }
        if(user.getRole().equals("USER")) {
            return "redirect:/foods/cart";
        }
        return "redirect:/foods/all";
    }
}
