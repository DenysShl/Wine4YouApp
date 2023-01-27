package com.sommelier.wine4you.model.dto.taste;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiModel("Request WineTaste model information")
@Data
public class WineTasteRequestDto {
    @ApiModelProperty(value = "Wine taste name")
    @NotBlank
    @Size(min = 3, message = "Name taste should have at least 3 characters")
    private String nameTaste;
}
