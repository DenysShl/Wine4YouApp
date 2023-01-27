package com.sommelier.wine4you.model.dto.style;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiModel("Request Style model information")
@Data
public class WineStyleRequestDto {
    @ApiModelProperty(value = "Wine style name")
    @NotBlank
    @Size(min = 3, message = "Name style should have at least 3 characters")
    private String nameStyle;
}
