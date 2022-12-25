package com.sommelier.wine4you.model.mapper;

public interface GenericMapper<T,V,U> {
    T toDto(V v);

    V toModel(U u);
}
