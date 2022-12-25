package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.model.ShoppingCart;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.repository.ShoppingCartRepository;
import com.sommelier.wine4you.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return null;
    }

    @Override
    public void registerNewShoppingCart(User user) {

    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setOrder(null);
        shoppingCartRepository.save(shoppingCart);
    }
}
