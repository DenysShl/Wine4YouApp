package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Address;
import com.sommelier.wine4you.model.Cart;
import com.sommelier.wine4you.model.dto.shoppingcart.CartRequestDto;
import com.sommelier.wine4you.model.dto.shoppingcart.CartResponseDto;
import com.sommelier.wine4you.model.enums.PaymentType;
import com.sommelier.wine4you.model.enums.ShippingType;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import com.sommelier.wine4you.service.AddressService;
import com.sommelier.wine4you.service.ItemService;
import com.sommelier.wine4you.service.UserService;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements MapperToModel<Cart, CartRequestDto>,
        MapperToDto<CartResponseDto, Cart> {
    private static final String DELIMITER = ",";
    private final ItemService itemService;
    private final UserService userService;
    private final AddressService addressService;
    private final AddressMapperImpl addressMapper;
    private final ItemMapperImpl itemMapper;

    @Autowired
    public CartMapperImpl(ItemService itemService,
                          UserService userService,
                          AddressService addressService,
                          AddressMapperImpl addressMapper,
                          ItemMapperImpl itemMapper) {
        this.itemService = itemService;
        this.userService = userService;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public Cart toModel(CartRequestDto cartRequestDto) {
        Cart cart = new Cart();
        cart.setItems(
                cartRequestDto.getItemRequestDtos()
                        .stream()
                        .map(p -> itemService.create(itemMapper.toModel(p)))
                        .collect(Collectors.toList())
        );
        cart.setDeliveryPrice(cartRequestDto.getDeliveryPrice());
        cart.setDiscount(cartRequestDto.getDiscount());
        cart.setTotalAmount(cartRequestDto.getTotalAmount());

        cart.setDontCallMeBack(cartRequestDto.getDontCallMeBack());
        cart.setBuyAsGift(cartRequestDto.getBuyAsGift());
        cart.setPaymentType(PaymentType.valueOf(cartRequestDto.getPayment().toUpperCase()));
        ShippingType shippingType
                = ShippingType.valueOf(cartRequestDto.getShipping().toUpperCase());
        cart.setShipping(shippingType);

        switch (shippingType) {
            case OFFICES:
                cart.setAddress(addressService.create(
                        getAddress(cartRequestDto.getPostalOffice())));
                break;
            case MARKETPLACE:
                cart.setAddress(addressService.create(
                        getAddress(cartRequestDto.getWine4youShop())));
                break;

            default:
                cart.setAddress(addressService.create(
                        addressMapper.toModel(cartRequestDto.getAddressRequestDto())));
        }
        cart.setUser(userService.getByEmail(cartRequestDto.getEmail()));
        return cart;
    }

    @Override
    public CartResponseDto toDto(Cart cart) {
        CartResponseDto responseDto = new CartResponseDto();
        responseDto.setId(cart.getId());
        responseDto.setItems(
                cart.getItems()
                        .stream()
                        .map(item -> itemMapper.toDto(item))
                        .toList()
        );
        responseDto.setUser(cart.getUser());
        responseDto.setDeliveryPrice(cart.getDeliveryPrice());
        responseDto.setDiscount(cart.getDiscount());
        responseDto.setTotalAmount(cart.getTotalAmount());
        responseDto.setAddressResponseDto(addressMapper.toDto(cart.getAddress()));
        responseDto.setDontCallMeBack(cart.getDontCallMeBack());
        responseDto.setBuyAsGift(cart.getBuyAsGift());
        responseDto.setShipping(cart.getShipping());
        responseDto.setPayment(cart.getPaymentType());
        responseDto.setCreatedDate(cart.getCreatedDate());
        return responseDto;
    }

    private Address getAddress(String str) {
        String[] strings = str.replace(" ", "").split(DELIMITER);
        Address address = new Address();
        address.setCity(strings[0]);
        address.setStreet(strings[1]);
        address.setApartment(strings[2]);
        return address;
    }
}
