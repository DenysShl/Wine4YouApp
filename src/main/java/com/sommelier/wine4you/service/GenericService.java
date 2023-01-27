package com.sommelier.wine4you.service;

import java.util.List;

public interface GenericService<T> {
    T create(T t);

    T getById(Long id);

    List<T> getAll();

    boolean deleteById(Long id);

    T update(Long id, T t);
}
