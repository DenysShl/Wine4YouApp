package com.sommelier.wine4you.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sommelier.wine4you.model.enums.PaymentType;
import com.sommelier.wine4you.model.enums.ShippingType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Item.class)
    private List<Item> items;
    @OneToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    private User user;
    @Column(name = "delivery_price")
    private Double deliveryPrice;
    private Integer discount;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @OneToOne(cascade = CascadeType.MERGE)
    private Address address;
    @Column(name = "is_called")
    private Boolean dontCallMeBack;
    @Column(name = "buy_as_gift")
    private Boolean buyAsGift;
    @Enumerated(value = EnumType.STRING)
    private ShippingType shipping;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cart cart = (Cart) o;

        if (!Objects.equals(id, cart.id)) {
            return false;
        }
        if (!Objects.equals(items, cart.items)) {
            return false;
        }
        if (!Objects.equals(user, cart.user)) {
            return false;
        }
        if (!Objects.equals(deliveryPrice, cart.deliveryPrice)) {
            return false;
        }
        if (!Objects.equals(discount, cart.discount)) {
            return false;
        }
        if (!Objects.equals(totalAmount, cart.totalAmount)) {
            return false;
        }
        if (!Objects.equals(address, cart.address)) {
            return false;
        }
        if (!Objects.equals(dontCallMeBack, cart.dontCallMeBack)) {
            return false;
        }
        if (!Objects.equals(buyAsGift, cart.buyAsGift)) {
            return false;
        }
        if (shipping != cart.shipping) {
            return false;
        }
        if (paymentType != cart.paymentType) {
            return false;
        }
        return Objects.equals(createdDate, cart.createdDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (deliveryPrice != null ? deliveryPrice.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (dontCallMeBack != null ? dontCallMeBack.hashCode() : 0);
        result = 31 * result + (buyAsGift != null ? buyAsGift.hashCode() : 0);
        result = 31 * result + (shipping != null ? shipping.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
