package com.sommelier.wine4you.model.dto.order;

import com.sommelier.wine4you.model.Item;
import com.sommelier.wine4you.model.enums.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private List<Item> items;
    private Long userId;
    private String orderTackingNumber;
    private Double deliveryPrice;
    private Integer discount;
    private BigDecimal totalPrice;
    private PaymentType payment;
    private String status;
    private LocalDateTime dateCreated;
}
