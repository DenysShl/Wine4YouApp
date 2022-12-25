package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.model.dto.WineTasteRequestDto;
import com.sommelier.wine4you.model.dto.WineTasteResponseDto;
import com.sommelier.wine4you.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class WineTasteMapperImpl implements GenericMapper<WineTasteResponseDto,
        WineTaste, WineTasteRequestDto> {
    @Override
    public WineTasteResponseDto toDto(WineTaste wineTaste) {
        WineTasteResponseDto responseDto = new WineTasteResponseDto();
        responseDto.setId(wineTaste.getId());
        responseDto.setNameTaste(wineTaste.getNameTaste());
        return responseDto;
    }

    @Override
    public WineTaste toModel(WineTasteRequestDto wineTasteRequestDto) {
        WineTaste wineTaste = new WineTaste();
        wineTaste.setNameTaste(wineTasteRequestDto.getNameTaste());
        return wineTaste;
    }
}
