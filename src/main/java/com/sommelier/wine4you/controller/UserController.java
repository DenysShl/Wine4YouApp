package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.user.UserRequestDto;
import com.sommelier.wine4you.model.dto.user.UserResponseDto;
import com.sommelier.wine4you.model.mapper.impl.UserMapperImpl;
import com.sommelier.wine4you.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Rest APIs for manage Users data")
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapperImpl userMapper;

    public UserController(UserService userService, UserMapperImpl userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Create User REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponseDto> create(
            @Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userMapper.toDto(
                userService.create(userMapper.toModel(userRequestDto))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Users REST API")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll()
                .stream()
                .map(userMapper::toDto)
                .toList());
    }

    @ApiOperation(value = "Get User by 'Id' REST API")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.getById(id)));
    }

    @ApiOperation(value = "Update User By 'Id' REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateById(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userMapper.toDto(
                userService.update(id, userMapper.toModel(userRequestDto))));
    }

    @ApiOperation(value = "Delete User by 'Id' REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Success, deleted user by id " + id);
    }
}
