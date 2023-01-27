package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Meal;
import com.sommelier.wine4you.repository.MealRepository;
import com.sommelier.wine4you.service.MealService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal create(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal getById(Long id) {
        return mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal", "Id", String.valueOf(id))
        );
    }

    @Override
    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        mealRepository.deleteById(id);
        return mealRepository.existsById(id);
    }

    @Override
    public Meal findByName(String name) {
        return mealRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Meal", "mealName", name)
        );
    }

    @Override
    public Meal update(Long id, Meal meal) {
        meal.setId(id);
        return mealRepository.save(meal);
    }
}
