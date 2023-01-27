package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Meal;
import com.sommelier.wine4you.model.dto.meal.MealRequestDto;
import com.sommelier.wine4you.model.dto.meal.MealResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class MealMapperImpl implements MapperToDto<MealResponseDto, Meal>,
        MapperToModel<Meal, MealRequestDto> {
    @Override
    public MealResponseDto toDto(Meal meal) {
        MealResponseDto responseDto = new MealResponseDto();
        responseDto.setId(meal.getId());
        responseDto.setMealName(meal.getName());
        return responseDto;
    }

    @Override
    public Meal toModel(MealRequestDto mealRequestDto) {
        Meal meal = new Meal();
        meal.setName(mealRequestDto.getMealName());
        return meal;
    }
}
