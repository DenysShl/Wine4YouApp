package com.sommelier.wine4you.model.dto.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiModel("Request Event model information")
@Data
public class EventRequestDto {
    @ApiModelProperty(value = "Wine event name")
    @NotBlank
    @Size(min = 3, message = "Name event should have at least 3 characters")
    private String nameEvent;
}
