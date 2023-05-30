package com.foodapp.service;

import com.foodapp.entity.Food;
import com.foodapp.repo.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + id));
    }

    // Other methods if needed
}
