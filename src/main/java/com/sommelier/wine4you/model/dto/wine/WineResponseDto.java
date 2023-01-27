package com.sommelier.wine4you.model.dto.wine;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class WineResponseDto {
    private Long id;
    private String brand;
    private String country;
    private BigDecimal price;
    private String title;
    private Boolean inStock;
    private String name;
    private String wineStyleName;
    private String wineTypeName;
    private String wineTasteName;
    private String eventName;
    private String meal;
    private double capacity;
    private List<Long> imageIds;
    private String description;
}
