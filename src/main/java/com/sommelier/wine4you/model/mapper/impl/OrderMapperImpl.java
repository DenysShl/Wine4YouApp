package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.model.dto.order.OrderResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements MapperToDto<OrderResponseDto, Order> {
    @Override
    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getItems(),
                order.getUser().getId(),
                order.getOrderTackingNumber(),
                order.getDeliveryPrice(),
                order.getDiscount(),
                order.getTotalAmount(),
                order.getPaymentType(),
                order.getOrderStatus(),
                order.getCreatedDate()
        );
    }
}
