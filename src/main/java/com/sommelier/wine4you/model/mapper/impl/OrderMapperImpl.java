package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.model.dto.order.OrderResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements MapperToDto<OrderResponseDto, Order> {
    private final AddressMapperImpl addressMapper;
    private final ItemMapperImpl itemMapper;

    @Autowired
    public OrderMapperImpl(AddressMapperImpl addressMapper,
                           ItemMapperImpl itemMapper) {
        this.addressMapper = addressMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setItems(
                order.getItems()
                        .stream()
                        .map(itemMapper::toDto)
                        .toList()
        );
        responseDto.setUserId(order.getUser().getId());
        responseDto.setOrderTackingNumber(order.getOrderTackingNumber());
        responseDto.setDeliveryPrice(order.getCart().getDeliveryPrice());
        responseDto.setDiscount(order.getCart().getDiscount());
        responseDto.setTotalAmount(order.getCart().getTotalAmount());
        responseDto.setAddressResponseDto(addressMapper.toDto(
                order.getCart().getAddress()));
        responseDto.setDontCallMeBack(order.getCart().getDontCallMeBack());
        responseDto.setBuyAsGift(order.getCart().getBuyAsGift());
        responseDto.setShipping(order.getCart().getShipping());
        responseDto.setPayment(order.getCart().getPaymentType());
        responseDto.setOrderStatus(order.getOrderStatus());
        responseDto.setDateCreated(order.getCreatedDate());
        return responseDto;
    }
}
