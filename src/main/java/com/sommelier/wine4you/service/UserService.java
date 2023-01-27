package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.User;

public interface UserService {
    User getByEmail(String email);

    User getByPhoneOrEmail(String phone, String email);
}
