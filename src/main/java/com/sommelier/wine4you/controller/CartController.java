package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.exception.PaymentException;
import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.payment.PaymentRequestDto;
import com.sommelier.wine4you.model.dto.shoppingcart.CartRequestDto;
import com.sommelier.wine4you.model.dto.shoppingcart.CartResponseDto;
import com.sommelier.wine4you.model.enums.PaymentStatus;
import com.sommelier.wine4you.model.mapper.impl.CartMapperImpl;
import com.sommelier.wine4you.model.mapper.impl.PaymentMapperImpl;
import com.sommelier.wine4you.service.CartService;
import com.sommelier.wine4you.service.OrderService;
import com.sommelier.wine4you.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Rest APIs for Cart resources")
@RestController
@RequestMapping("api/v1/carts")
public class CartController {
    private final CartService cartService;
    private final CartMapperImpl cartMapper;
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentMapperImpl paymentMapper;

    @Autowired
    public CartController(CartService cartService,
                          CartMapperImpl cartMapper,
                          UserService userService,
                          OrderService orderService,
                          PaymentMapperImpl paymentMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.userService = userService;
        this.orderService = orderService;
        this.paymentMapper = paymentMapper;
    }

    @ApiOperation(value = "Create Cart REST API")
    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody CartRequestDto cartRequestDto,
            @RequestBody PaymentRequestDto paymentRequestDto) {
        Cart cart = cartMapper.toModel(cartRequestDto);
        cartService.addItemsToCart(cart);

//        if (!paymentRequestDto.getPaymentStatus()
//                .equals(PaymentStatus.COMPLETE)) {
//            throw new PaymentException("Payment card do not support");
//        }
//        orderService.completeOrder(cart, paymentMapper.toModel(paymentRequestDto));
        return new ResponseEntity<>("Order created successfully!",
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get 'Cart' by user REST API")
    @GetMapping("/by-usrEmail")
    public ResponseEntity<CartResponseDto> getByUser(@RequestParam("emailUser") String emailUser) {
        User user = userService.getByEmail(emailUser);
        return ResponseEntity.ok(cartMapper.toDto(cartService.getByUser(user)));
    }
}
