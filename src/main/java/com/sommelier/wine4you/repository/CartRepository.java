package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

    List<Cart> findAllByUser(User user);

    List<Cart> deleteByUser(User user);
}
