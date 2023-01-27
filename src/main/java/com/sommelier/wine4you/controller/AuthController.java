package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.config.SecurityConfig;
import com.sommelier.wine4you.model.dto.user.UserLoginDto;
import com.sommelier.wine4you.model.dto.user.UserSignUpDto;
import com.sommelier.wine4you.repository.UserRepository;
import com.sommelier.wine4you.security.jwt.JwtAuthResponse;
import com.sommelier.wine4you.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Auth controller exposes sign-in and sign-up REST APIs")
@Import(SecurityConfig.class)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationService authenticationService,
                          UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "REST API to authentication user to Wine4You app")
    @PostMapping("sign-in")
    public ResponseEntity<JwtAuthResponse> authentication(
            @Valid @RequestBody UserLoginDto loginDto) {
        String token = authenticationService.authentication(loginDto);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @ApiOperation(value = "REST API to register user to Wine4You app")
    @PostMapping("sign-up")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserSignUpDto signUpDto) {
        if (userRepository.existsByPhone(signUpDto.getPhone())) {
            return new ResponseEntity<>("Phone is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        authenticationService.registerUser(signUpDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
