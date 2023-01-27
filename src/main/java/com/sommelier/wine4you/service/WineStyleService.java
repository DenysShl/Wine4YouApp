package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.WineStyle;

public interface WineStyleService extends GenericService<WineStyle> {
    WineStyle findByName(String name);
}
