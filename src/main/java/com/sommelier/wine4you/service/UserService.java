package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.User;

public interface UserService extends GenericService<User> {
    User getByEmail(String email);

    User getByPhoneOrEmail(String phone, String email);
}
