package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Address;
import com.sommelier.wine4you.model.dto.address.AddressRequestDto;
import com.sommelier.wine4you.model.dto.address.AddressResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements MapperToModel<Address, AddressRequestDto>,
        MapperToDto<AddressResponseDto,Address> {
    @Override
    public Address toModel(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setCity(addressRequestDto.getCity());
        address.setStreet(addressRequestDto.getStreet());
        address.setApartment(addressRequestDto.getApartment());
        return address;
    }

    @Override
    public AddressResponseDto toDto(Address address) {
        AddressResponseDto responseDto = new AddressResponseDto();
        responseDto.setId(address.getId());
        responseDto.setCity(address.getCity());
        responseDto.setStreet(address.getStreet());
        responseDto.setApartment(address.getApartment());
        return responseDto;
    }
}
