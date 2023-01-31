package com.sommelier.wine4you.model;

import com.sommelier.wine4you.model.enums.PaymentStatus;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String cardNumber;
    @Transient
    private int expiryYear;
    @Transient
    private int expiryMonth;
    @Transient
    private int cvc;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Payment(String cardName,
                   String cardNumber,
                   int expiryYear,
                   int expiryMonth,
                   int cvc,
                   PaymentStatus paymentStatus) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.cvc = cvc;
        this.paymentStatus = paymentStatus;
    }
}
