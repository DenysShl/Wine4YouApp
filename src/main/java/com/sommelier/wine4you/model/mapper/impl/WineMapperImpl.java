package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.model.dto.WineRequestDto;
import com.sommelier.wine4you.model.dto.WineResponseDto;
import com.sommelier.wine4you.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class WineMapperImpl implements GenericMapper<WineResponseDto, Wine, WineRequestDto> {
    @Override
    public WineResponseDto toDto(Wine wine) {
        WineResponseDto wineResponseDto = new WineResponseDto();
        wineResponseDto.setId(wine.getId());
        wineResponseDto.setBrand(wine.getBrand());
        wineResponseDto.setCountry(wine.getCountry());
        wineResponseDto.setPrice(wine.getPrice());
        wineResponseDto.setTitle(wine.getTitle());
        wineResponseDto.setInStock(wine.getInStock());
        wineResponseDto.setName(wine.getName());
        wineResponseDto.setWineStyle(wine.getWineStyle());
        wineResponseDto.setWineType(wine.getWineType());
        wineResponseDto.setWineTaste(wine.getWineTaste());
        wineResponseDto.setCapacity(wine.getCapacity());
        wineResponseDto.setEvent(wine.getEvent());
        wineResponseDto.setImages(wine.getImages());
        wineResponseDto.setDescription(wine.getDescription());
        return wineResponseDto;
    }

    @Override
    public Wine toModel(WineRequestDto wineRequestDto) {
        Wine wine = new Wine();
        wine.setBrand(wineRequestDto.getBrand());
        wine.setCountry(wineRequestDto.getCountry());
        wine.setPrice(wineRequestDto.getPrice());
        wine.setTitle(wineRequestDto.getTitle());
        wine.setInStock(wineRequestDto.getInStock());
        wine.setName(wineRequestDto.getName());
        wine.setWineStyle(wineRequestDto.getWineStyle());
        wine.setWineType(wineRequestDto.getWineType());
        wine.setWineTaste(wineRequestDto.getWineTaste());
        wine.setCapacity(wineRequestDto.getCapacity());
        wine.setEvent(wineRequestDto.getEvent());
        wine.setImages(wineRequestDto.getImages());
        wine.setDescription(wineRequestDto.getDescription());
        return wine;
    }
}
