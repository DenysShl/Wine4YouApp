package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.model.ShoppingCart;
import com.sommelier.wine4you.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
