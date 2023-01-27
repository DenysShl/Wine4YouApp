package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.User;

public interface CartService {
    Cart getByUser(User user);

    Cart registerNewShoppingCart(User user);

    Cart addItemsToCart(Cart cart);

    void clear(Cart cart);
}
