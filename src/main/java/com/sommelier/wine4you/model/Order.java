package com.sommelier.wine4you.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Item> items;

    @OneToOne
    private Cart cart;
    @Column(name = "order_tracking_number")
    private String orderTackingNumber;

    @OneToOne
    private Payment payment;
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_created")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime createdDate;

    @PrePersist
    private void init() {
        createdDate = LocalDateTime.now();
    }

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
        if (!Objects.equals(cart, order.cart)) {
            return false;
        }
        if (!Objects.equals(orderTackingNumber, order.orderTackingNumber)) {
            return false;
        }
        if (!Objects.equals(payment, order.payment)) {
            return false;
        }
        if (!Objects.equals(orderStatus, order.orderStatus)) {
            return false;
        }
        if (!Objects.equals(user, order.user)) {
            return false;
        }
        return Objects.equals(createdDate, order.createdDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        result = 31 * result + (orderTackingNumber != null ? orderTackingNumber.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
