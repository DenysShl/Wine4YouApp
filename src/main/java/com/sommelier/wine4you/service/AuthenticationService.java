package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.user.UserLoginDto;
import com.sommelier.wine4you.model.dto.user.UserSignUpDto;

public interface AuthenticationService {
    String authentication(UserLoginDto loginDto);

    User registerUser(UserSignUpDto signUpDto);
}















