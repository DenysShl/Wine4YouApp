package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findOrdersByUser(User user);
}
