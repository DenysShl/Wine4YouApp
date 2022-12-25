package com.sommelier.wine4you.model.dto;

import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.model.Image;
import com.sommelier.wine4you.model.WineStyle;
import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.model.enums.WineType;
import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class WineRequestDto {
    @NotBlank
    @Size(min = 2, message = "Wine brand should have at least 2 characters")
    private String brand;
    @NotEmpty
    private String country;
    @NotEmpty
    private BigDecimal price;
    private String title;
    private Boolean inStock;
    @NotEmpty
    private String name;
    private WineStyle wineStyle;
    private WineTaste wineTaste;
    private Event event;
    private WineType wineType;
    private double capacity;
    private Set<Image> images;
    private String description;
}
