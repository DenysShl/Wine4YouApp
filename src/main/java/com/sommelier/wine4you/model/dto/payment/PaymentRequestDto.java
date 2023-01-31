package com.sommelier.wine4you.model.dto.payment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    @NotEmpty
    private String cardName;
    @NotEmpty
    @Size(min = 16, max = 16)
    private String cardNumber;
    @NotEmpty
    private int expiryYear;
    @NotEmpty
    private int expiryMonth;
    @NotEmpty
    private int cvc;
    @NotEmpty
    private String paymentStatus;
}
