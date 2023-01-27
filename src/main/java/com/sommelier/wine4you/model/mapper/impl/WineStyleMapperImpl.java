package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.WineStyle;
import com.sommelier.wine4you.model.dto.style.WineStyleRequestDto;
import com.sommelier.wine4you.model.dto.style.WineStyleResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class WineStyleMapperImpl implements MapperToDto<WineStyleResponseDto,
        WineStyle>, MapperToModel<WineStyle, WineStyleRequestDto> {
    @Override
    public WineStyleResponseDto toDto(WineStyle wineStyle) {
        WineStyleResponseDto responseDto = new WineStyleResponseDto();
        responseDto.setId(wineStyle.getId());
        responseDto.setNameStyle(wineStyle.getNameStyle());
        return responseDto;
    }

    @Override
    public WineStyle toModel(WineStyleRequestDto wineStyleRequestDto) {
        WineStyle wineStyle = new WineStyle();
        wineStyle.setNameStyle(wineStyleRequestDto.getNameStyle());
        return wineStyle;
    }
}
