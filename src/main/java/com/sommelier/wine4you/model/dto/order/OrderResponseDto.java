package com.sommelier.wine4you.model.dto.order;

import com.sommelier.wine4you.model.dto.address.AddressResponseDto;
import com.sommelier.wine4you.model.dto.item.ItemResponseDto;
import com.sommelier.wine4you.model.enums.PaymentType;
import com.sommelier.wine4you.model.enums.ShippingType;
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
    private List<ItemResponseDto> items;
    private Long userId;
    private String orderTackingNumber;
    private Double deliveryPrice;
    private Integer discount;
    private BigDecimal totalAmount;
    private AddressResponseDto addressResponseDto;
    private Boolean dontCallMeBack;
    private Boolean buyAsGift;
    private ShippingType shipping;
    private PaymentType payment;
    private String orderStatus;
    private LocalDateTime dateCreated;
}
