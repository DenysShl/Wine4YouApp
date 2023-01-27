package com.sommelier.wine4you.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinTable(name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private List<Item> items;
    @Column(name = "delivery_price")
    private Double deliveryPrice;
    private Integer discount;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

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

        Order order = (Order) o;

        if (!Objects.equals(id, order.id)) {
            return false;
        }
        if (!Objects.equals(items, order.items)) {
            return false;
        }
        if (!Objects.equals(deliveryPrice, order.deliveryPrice)) {
            return false;
        }
        if (!Objects.equals(discount, order.discount)) {
            return false;
        }
        if (!Objects.equals(totalAmount, order.totalAmount)) {
            return false;
        }
        return Objects.equals(createdDate, order.createdDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (deliveryPrice != null ? deliveryPrice.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", items=" + items
                + ", deliveryPrice=" + deliveryPrice
                + ", discount=" + discount
                + ", totalAmount=" + totalAmount
                + ", createdDate=" + createdDate
                + '}';
    }
}
