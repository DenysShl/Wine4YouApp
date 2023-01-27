package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.repository.UserRepository;
import com.sommelier.wine4you.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "Email", email)
        );
    }

    @Override
    public User getByPhoneOrEmail(String phone, String email) {
        return userRepository.findByPhoneOrEmail(phone, email).orElseThrow(
                () -> new ResourceNotFoundException("User","Phone or Email", phone + "/" + email)
        );
    }
}
