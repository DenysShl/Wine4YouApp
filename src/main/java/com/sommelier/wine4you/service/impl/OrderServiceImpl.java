package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.Item;
import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.model.Payment;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.repository.OrderRepository;
import com.sommelier.wine4you.service.CartService;
import com.sommelier.wine4you.service.OrderService;
import com.sommelier.wine4you.service.PaymentService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentService paymentService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CartService cartService,
                            PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional
    public Order completeOrder(Cart cart, Payment payment) {
        Order order = new Order();
        order.setCart(cart);
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        order.setUser(cart.getUser());
        order.setOrderStatus("Order inprogress");
        List<Item> currentItems = new ArrayList<>(cart.getItems());
        order.setItems(currentItems);
        order.setPayment(paymentService.create(payment));
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
