package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.WineTaste;
import java.util.List;

public interface WineTasteService {
    WineTaste create(WineTaste wineTaste);

    WineTaste findById(Long id);

    List<WineTaste> getAll();

    void deleteById(Long id);

    WineTaste findByTasteName(String name);
}
