package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.dto.user.UserRequestDto;
import com.sommelier.wine4you.model.dto.user.UserResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements MapperToDto<UserResponseDto, User>,
        MapperToModel<User, UserRequestDto> {
    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(user.getPassword());
        responseDto.setPhone(user.getPhone());
        responseDto.setBirthday(user.getBirthday());
        responseDto.setAddress(user.getAddress());
        responseDto.setCart(user.getCart());
        responseDto.setRegistrationDate(user.getRegistrationDate());
        responseDto.setRoles(user.getRoles());
        responseDto.setDeleted(user.isDeleted());
        return responseDto;
    }

    @Override
    public User toModel(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setPassword(userRequestDto.getPassword());
        user.setBirthday(userRequestDto.getBirthday());
        user.setAddress(userRequestDto.getAddress());
        user.setCart(userRequestDto.getCart());
        user.setRegistrationDate(userRequestDto.getRegistrationDate());
        user.setRoles(userRequestDto.getRoles());
        user.setDeleted(userRequestDto.isDeleted());
        return user;
    }
}
