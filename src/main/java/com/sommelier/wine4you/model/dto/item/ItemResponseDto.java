package com.sommelier.wine4you.model.dto.item;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemResponseDto {
    private Long id;
    private Long wineId;
    private Integer wineQuantity;
    private BigDecimal total;
}
