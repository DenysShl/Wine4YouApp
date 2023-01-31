package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.repository.UserRepository;
import com.sommelier.wine4you.service.UserService;
import java.util.List;
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
                () -> new ResourceNotFoundException("User", "Phone or Email", phone + "/" + email)
        );
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", String.valueOf(id))
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        User user = getById(id);
        update(id, user);
        return userRepository.existsById(id);

    }

    @Override
    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
}
