package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.WineTaste;

public interface WineTasteService extends GenericService<WineTaste> {
    WineTaste findByName(String name);
}
