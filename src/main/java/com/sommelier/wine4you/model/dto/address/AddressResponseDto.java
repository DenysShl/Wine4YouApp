package com.sommelier.wine4you.model.dto.address;

import lombok.Data;

@Data
public class AddressResponseDto {
    private Long id;
    private String city;
    private String street;
    private String apartment;
}
