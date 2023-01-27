package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Image;
import com.sommelier.wine4you.model.dto.image.ImageWineResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import org.springframework.stereotype.Component;

@Component
public class ImageMapperImpl implements MapperToDto<ImageWineResponseDto, Image> {

    @Override
    public ImageWineResponseDto toDto(Image image) {
        ImageWineResponseDto imageWineResponseDto = new ImageWineResponseDto();
        imageWineResponseDto.setId(image.getId());
        imageWineResponseDto.setName(image.getName());
        imageWineResponseDto.setType(image.getType());
        imageWineResponseDto.setUrlPath("/api/wines/"
                + image.getWine().getId() + "/images/"
                + imageWineResponseDto.getId());
        return imageWineResponseDto;
    }
}
