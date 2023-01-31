package com.sommelier.wine4you.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Wine wine;
    private Integer quantity;
    private BigDecimal total;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(wine, item.wine) && Objects.equals(quantity, item.quantity)
                && Objects.equals(total, item.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wine, quantity, total);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", wine=" + wine
                + ", quantity=" + quantity
                + ", total=" + total
                + '}';
    }
}
