package com.sommelier.wine4you.model.dto.wine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WineRequestFilterDto {
    private String brand;
    private String event;
    private String country;
    private String meal;
    private List<String> wineStyle;
    private List<String> wineTaste;
    @Min(1)
    private BigDecimal priceMin;
    private BigDecimal priceMax;
}
