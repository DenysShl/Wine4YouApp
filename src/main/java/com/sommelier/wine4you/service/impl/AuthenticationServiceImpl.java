package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.model.Role;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.user.UserLoginDto;
import com.sommelier.wine4you.model.dto.user.UserSignUpDto;
import com.sommelier.wine4you.model.mapper.impl.AddressMapperImpl;
import com.sommelier.wine4you.repository.RoleRepository;
import com.sommelier.wine4you.repository.UserRepository;
import com.sommelier.wine4you.security.jwt.JwtTokenProvider;
import com.sommelier.wine4you.service.AddressService;
import com.sommelier.wine4you.service.AuthenticationService;
import com.sommelier.wine4you.service.CartService;
import java.time.LocalDateTime;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final AddressService addressService;
    private final AddressMapperImpl addressMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserRepository userRepository,
                                     CartService cartService,
                                     AddressService addressService,
                                     AddressMapperImpl addressMapper,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String authentication(UserLoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getPhoneOrEmail(), loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    @Override
    public User registerUser(UserSignUpDto signUpDto) {
        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPhone(signUpDto.getPhone());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setBirthday(signUpDto.getBirthday());
        user.setAddress(addressService.create(addressMapper.toModel(signUpDto.getAddress())));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
        cartService.registerNewShoppingCart(user);
        return user;
    }
}
