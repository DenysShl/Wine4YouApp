package com.sommelier.wine4you.model.dto.shoppingcart;

import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.address.AddressResponseDto;
import com.sommelier.wine4you.model.dto.item.ItemResponseDto;
import com.sommelier.wine4you.model.enums.PaymentType;
import com.sommelier.wine4you.model.enums.ShippingType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CartResponseDto {
    private Long id;
    private List<ItemResponseDto> items;
    private User user;
    private Double deliveryPrice;
    private Integer discount;
    private BigDecimal totalAmount;
    private AddressResponseDto addressResponseDto;
    private Boolean dontCallMeBack;
    private Boolean buyAsGift;
    private ShippingType shipping;
    private PaymentType payment;
    private LocalDateTime createdDate;
}
