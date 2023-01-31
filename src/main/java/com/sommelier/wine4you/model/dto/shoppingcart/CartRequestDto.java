package com.sommelier.wine4you.model.dto.shoppingcart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sommelier.wine4you.model.dto.address.AddressRequestDto;
import com.sommelier.wine4you.model.dto.item.ItemRequestDto;
import com.sommelier.wine4you.model.dto.payment.PaymentRequestDto;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CartRequestDto {
    private List<ItemRequestDto> itemRequestDtos;
    private Double deliveryPrice;
    private Integer discount;
    private BigDecimal totalAmount;
    private String email;
    private AddressRequestDto addressRequestDto;
    private Boolean dontCallMeBack;
    private Boolean buyAsGift;
    private String shipping;
    private String payment;
    private String postalOffice;
    private String wine4youShop;
    private PaymentRequestDto paymentRequestDto;
}
