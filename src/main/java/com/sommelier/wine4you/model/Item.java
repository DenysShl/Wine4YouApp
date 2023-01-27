package com.sommelier.wine4you.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Setter;

@Entity
@Setter
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Order order;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Cart cart;
    @ManyToOne
    private Wine wine;
    private Integer quantity;
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Wine getWine() {
        return wine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(order, item.order)
                && Objects.equals(wine, item.wine) && Objects.equals(quantity, item.quantity)
                && Objects.equals(total, item.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, wine, quantity, total);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", order=" + order
                + ", wine=" + wine
                + ", quantity=" + quantity
                + ", total=" + total
                + '}';
    }
}
