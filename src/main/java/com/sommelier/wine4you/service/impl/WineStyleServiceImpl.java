package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.WineStyle;
import com.sommelier.wine4you.repository.WineStyleRepository;
import com.sommelier.wine4you.service.WineStyleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class WineStyleServiceImpl implements WineStyleService {
    private final WineStyleRepository wineStyleRepository;

    @Autowired
    public WineStyleServiceImpl(WineStyleRepository wineStyleRepository) {
        this.wineStyleRepository = wineStyleRepository;
    }

    @Override
    public WineStyle create(WineStyle wineStyle) {
        return wineStyleRepository.save(wineStyle);
    }

    @Override
    public WineStyle findById(Long id) {
        return wineStyleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("WineStyle", "id", String.valueOf(id)));
    }

    @Override
    public List<WineStyle> getAll() {
        return wineStyleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        wineStyleRepository.deleteById(id);
    }

    @Override
    public WineStyle findByTasteName(String name) {
        return wineStyleRepository.findByNameStyle(name).orElseThrow(() ->
                new ResourceNotFoundException("WineStyle", "Style", name));
    }
}
