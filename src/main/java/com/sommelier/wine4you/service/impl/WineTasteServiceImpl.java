package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.repository.WineTasteRepository;
import com.sommelier.wine4you.service.WineTasteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public WineTaste getById(Long id) {
        return wineTasteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("WineTaste", "id", String.valueOf(id)));
    }

    @Override
    public List<WineTaste> getAll() {
        return wineTasteRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        wineTasteRepository.deleteById(id);
        return wineTasteRepository.existsById(id);
    }

    @Override
    public WineTaste findByName(String name) {
        return wineTasteRepository.findByNameTaste(name).orElseThrow(() ->
                new ResourceNotFoundException("WineTaste", "Taste", name));
    }

    @Override
    public boolean existsById(Long id) {
        return wineTasteRepository.existsById(id);
    }

    @Override
    public WineTaste update(Long id, WineTaste taste) {
        taste.setId(id);
        return wineTasteRepository.save(taste);
    }
}
