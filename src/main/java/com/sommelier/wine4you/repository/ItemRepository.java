package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Item;
import com.sommelier.wine4you.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByOrder(Order order);
}
