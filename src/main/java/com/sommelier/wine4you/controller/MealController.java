package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.meal.MealRequestDto;
import com.sommelier.wine4you.model.dto.meal.MealResponseDto;
import com.sommelier.wine4you.model.mapper.impl.MealMapperImpl;
import com.sommelier.wine4you.service.MealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Rest APIs for Wine meals resources")
@RestController
@RequestMapping("/api/v1/meals")
public class MealController {
    private final MealService mealService;
    private final MealMapperImpl mealMapper;

    public MealController(MealService mealService,
                          MealMapperImpl mealMapper) {
        this.mealService = mealService;
        this.mealMapper = mealMapper;
    }

    @ApiOperation(value = "Create Meal REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MealResponseDto> create(
            @Valid @RequestBody MealRequestDto mealRequestDto) {
        return new ResponseEntity<>(mealMapper.toDto(
                mealService.create(mealMapper.toModel(mealRequestDto))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Meal By 'Id' REST API")
    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getMealById(@PathVariable Long id) {
        return ResponseEntity.ok(mealMapper.toDto(mealService.getById(id)));
    }

    @ApiOperation(value = "Get All Meals REST API")
    @GetMapping()
    public ResponseEntity<List<MealResponseDto>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAll()
                .stream()
                .map(mealMapper::toDto)
                .toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Meal By 'Id' REST API")
    @PutMapping("/{id}")
    public ResponseEntity<MealResponseDto> updateById(
            @PathVariable("id") Long id,
            @Valid @RequestBody MealRequestDto mealRequestDto) {
        return ResponseEntity.ok(mealMapper.toDto(
                mealService.update(id, mealMapper.toModel(mealRequestDto))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Meal by 'Id' REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        mealService.deleteById(id);
        return ResponseEntity.ok("Success, deleted meal by id " + id);
    }

    @ApiOperation("Get meal by 'Name' REST API")
    @GetMapping("/by-name")
    public ResponseEntity<MealResponseDto> getByName(
            @RequestParam(defaultValue = "Cheese", value = "name") String mealName) {
        return ResponseEntity.ok(
                mealMapper.toDto(mealService.findByName(mealName)));
    }
}
