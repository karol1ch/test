package com.chomoncik.karol.camp_menu.service;

import com.chomoncik.karol.camp_menu.model.Meal;
import com.chomoncik.karol.camp_menu.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Optional<Meal> getMealById(Long mealId) {
        return mealRepository.findById(mealId);
    }
}
