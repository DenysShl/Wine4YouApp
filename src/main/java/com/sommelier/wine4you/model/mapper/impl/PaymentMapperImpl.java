package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Payment;
import com.sommelier.wine4you.model.dto.payment.PaymentRequestDto;
import com.sommelier.wine4you.model.enums.PaymentStatus;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperImpl implements MapperToModel<Payment, PaymentRequestDto> {
    @Override
    public Payment toModel(PaymentRequestDto paymentRequestDto) {
        return new Payment(
                paymentRequestDto.getCardName(),
                paymentRequestDto.getCardNumber(),
                paymentRequestDto.getExpiryYear(),
                paymentRequestDto.getExpiryMonth(),
                paymentRequestDto.getCvc(),
                PaymentStatus.valueOf(paymentRequestDto.getPaymentStatus())
        );
    }
}
