package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Meal;

public interface MealService extends GenericService<Meal> {
    Meal findByName(String name);
}
