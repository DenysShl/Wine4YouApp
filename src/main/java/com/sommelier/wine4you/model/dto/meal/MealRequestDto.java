package com.sommelier.wine4you.model.dto.meal;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiOperation("Request Meal model information")
@Data
public class MealRequestDto {
    @ApiModelProperty(value = "Meal name")
    @NotBlank
    @Size(min = 3, message = "Name meal should have at least 3 characters")
    private String mealName;
}
