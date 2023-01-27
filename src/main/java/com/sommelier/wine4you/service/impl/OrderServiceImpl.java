package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.repository.OrderRepository;
import com.sommelier.wine4you.service.CartService;
import com.sommelier.wine4you.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    @Override
    public Order completeOrder(Cart cart) {
        Order order = new Order();
        order.setCreatedDate(LocalDateTime.now());
        order.setUser(cart.getUser());
        orderRepository.save(order);
        cartService.clear(cart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderRepository.findOrdersByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Order", "User", user.toString())
        );
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order", "Id", String.valueOf(id))
        );
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        orderRepository.deleteById(id);
        return orderRepository.existsById(id);
    }

    @Override
    public Order update(Long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }
}
