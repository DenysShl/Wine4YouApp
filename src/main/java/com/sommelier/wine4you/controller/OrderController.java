package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.order.OrderResponseDto;
import com.sommelier.wine4you.model.mapper.impl.OrderMapperImpl;
import com.sommelier.wine4you.service.OrderService;
import com.sommelier.wine4you.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapperImpl orderMapper;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService,
                           OrderMapperImpl orderMapper,
                           UserService userService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll()
                .stream()
                .map(orderMapper::toDto)
                .toList());
    }

    @GetMapping("/by-userEmail/")
    public ResponseEntity<List<OrderResponseDto>> getAllOrderSByUser(
            @RequestParam String userEmail) {
        User userByEmail = userService.getByEmail(userEmail);
        return ResponseEntity.ok(orderService.getOrdersHistory(userByEmail)
                .stream()
                .map(orderMapper::toDto)
                .toList());
    }
}
