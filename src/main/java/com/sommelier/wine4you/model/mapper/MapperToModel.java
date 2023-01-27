package com.sommelier.wine4you.model.mapper;

public interface MapperToModel<V, T> {
    V toModel(T t);
}
