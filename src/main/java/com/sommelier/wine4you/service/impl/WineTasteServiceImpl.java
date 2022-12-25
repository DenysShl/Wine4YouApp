package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.repository.WineTasteRepository;
import com.sommelier.wine4you.service.WineTasteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class WineTasteServiceImpl implements WineTasteService {
    private final WineTasteRepository wineTasteRepository;

    @Autowired
    public WineTasteServiceImpl(WineTasteRepository wineTasteRepository) {
        this.wineTasteRepository = wineTasteRepository;
    }

    @Override
    public WineTaste create(WineTaste wineTaste) {
        return wineTasteRepository.save(wineTaste);
    }

    @Override
    public WineTaste findById(Long id) {
        return wineTasteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("WineTaste", "id", String.valueOf(id)));
    }

    @Override
    public List<WineTaste> getAll() {
        return wineTasteRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id);
    }

    @Override
    public WineTaste findByTasteName(String name) {
        return wineTasteRepository.findByNameTaste(name).orElseThrow(() ->
                new ResourceNotFoundException("WineTaste", "Taste", name));
    }
}
