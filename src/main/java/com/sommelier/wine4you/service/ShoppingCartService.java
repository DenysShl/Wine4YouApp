package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.ShoppingCart;
import com.sommelier.wine4you.model.User;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {
    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
