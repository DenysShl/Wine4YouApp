package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.repository.CartRepository;
import com.sommelier.wine4you.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("ShoppingCart", "User", user.getFirstName())
        );
    }

    @Override
    public Cart registerNewShoppingCart(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException(
                        "ShoppingCart", "UserId", String.valueOf(user.getId()))
        );
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addItemsToCart(Cart cart) {
        cart.setTotalAmount(cart.getTotalAmount());
        return cartRepository.save(cart);
    }

    @Override
    public void clear(Cart cart) {
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
