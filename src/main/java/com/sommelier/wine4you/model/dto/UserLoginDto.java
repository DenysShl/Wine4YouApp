package com.sommelier.wine4you.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotBlank(message = "PhoneOrEmail is mandatory")
    @Size(min = 5, message = "Login should have at least 5 characters")
    private String phoneOrEmail;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Size(max = 30, message = "The password must contain no more than 30 characters")
    private String password;
}
