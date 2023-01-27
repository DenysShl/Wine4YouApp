package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Address;
import com.sommelier.wine4you.repository.AddressRepository;
import com.sommelier.wine4you.service.AddressService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address","ID", String.valueOf(id))
        );
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        addressRepository.deleteById(id);
        return addressRepository.existsById(id);
    }

    @Override
    public Address update(Long id, Address address) {
        address.setId(id);
        return addressRepository.save(address);
    }
}
