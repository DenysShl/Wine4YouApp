package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.model.dto.taste.WineTasteRequestDto;
import com.sommelier.wine4you.model.dto.taste.WineTasteResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class WineTasteMapperImpl implements MapperToDto<WineTasteResponseDto,
        WineTaste>, MapperToModel<WineTaste, WineTasteRequestDto> {
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
