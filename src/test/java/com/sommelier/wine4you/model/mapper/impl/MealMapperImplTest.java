package com.sommelier.wine4you.model.mapper.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.sommelier.wine4you.model.Meal;
import com.sommelier.wine4you.model.dto.meal.MealRequestDto;
import com.sommelier.wine4you.model.dto.meal.MealResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MealMapperImplTest {
    private static MealMapperImpl mealMapper;
    private static Meal meal;
    private static Meal mealTest;
    private static MealResponseDto responseDto;
    private static MealRequestDto requestDto;

    @BeforeAll
    static void beforeAll() {
        mealMapper = new MealMapperImpl();
        meal = new Meal();
        meal.setId(1L);
        meal.setName("Meat");

        responseDto = new MealResponseDto();
        responseDto.setId(1L);
        responseDto.setMealName("Meat");

        requestDto = new MealRequestDto();
        requestDto.setMealName("Fish");

        mealTest = new Meal();
        mealTest.setName("Fish");
    }

    @Test
    void toDto_Ok() {
        MealResponseDto actual = mealMapper.toDto(meal);
        assertNotNull(actual);
        assertEquals(responseDto, actual);
        assertEquals(responseDto.getMealName(), actual.getMealName());
    }

    @Test
    void toModel_Ok() {
        Meal actual = mealMapper.toModel(requestDto);
        assertNotNull(actual);
        assertEquals(mealTest.getName(), actual.getName());
    }
}
