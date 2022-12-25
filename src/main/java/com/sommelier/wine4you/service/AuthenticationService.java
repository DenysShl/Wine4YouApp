package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.UserLoginDto;
import com.sommelier.wine4you.model.dto.UserSignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public String authentication(UserLoginDto loginDto);

    public User registerUser(UserSignUpDto signUpDto);
}















