package com.sommelier.wine4you.model.dto.address;

import lombok.Data;

@Data
public class AddressRequestDto {
    private String city;
    private String street;
    private String apartment;
}
