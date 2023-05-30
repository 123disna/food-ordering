package com.foodapp.controller;

import com.foodapp.entity.Food;
import com.foodapp.service.FoodService;
import com.foodapp.util.ImageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;
    private final ImageUtils imageUtils;

    public FoodController(FoodService foodService, ImageUtils imageUtils) {
        this.foodService = foodService;
        this.imageUtils = imageUtils;
    }

    @GetMapping("/new")
    public String showFoodForm(Model model) {
        model.addAttribute("food", new Food());
        return "food-form";
    }

    @PostMapping("/new")
    public String saveFood(@ModelAttribute("food") Food food, @RequestParam("imageFile") MultipartFile imageFile) throws IOException, IOException {
        if (!imageFile.isEmpty()) {
            food.setImage(imageFile.getBytes());
        }

        foodService.saveFood(food);
        return "redirect:/foods/" + food.getId();
    }

    @GetMapping("/{id}")
    public String showFoodDetails(@PathVariable("id") Long id, Model model) {
        Food food = foodService.getFoodById(id);
        model.addAttribute("food", food);
        model.addAttribute("imageUtils", imageUtils);
        return "foodDetails";
    }

    // Other methods if needed
}
