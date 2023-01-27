package com.sommelier.wine4you.model.mapper;

public interface MapperToDto<U,V> {
    U toDto(V v);
}
