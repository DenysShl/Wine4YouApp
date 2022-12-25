package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.exception.WineApiException;
import com.sommelier.wine4you.model.Image;
import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.repository.ImageDbRepository;
import com.sommelier.wine4you.repository.WineRepository;
import com.sommelier.wine4you.service.ImageService;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
public class ImageServiceImpl implements ImageService {
    private final WineRepository wineRepository;
    private final ImageDbRepository imageDbRepository;

    @Autowired
    public ImageServiceImpl(WineRepository wineRepository, ImageDbRepository imageDbRepository) {
        this.wineRepository = wineRepository;
        this.imageDbRepository = imageDbRepository;
    }

    @Override
    public Image create(Long wineId, MultipartFile multipartImage) {
        try {
            Wine wine = wineRepository.findById(wineId).orElseThrow(
                    () -> new ResourceNotFoundException("Wine", "id", String.valueOf(wineId))
            );
            Image dbImage = new Image();
            dbImage.setWine(wine);
            dbImage.setName(multipartImage.getName());
            dbImage.setType(multipartImage.getContentType());
            dbImage.setContent(multipartImage.getBytes());
            return imageDbRepository.save(dbImage);
        } catch (IOException e) {
            throw new RuntimeException("Can`t save image to database", e);
        }
    }

    @Override
    public byte[] getById(Long wineId, Long imageId) {
        Wine wine = wineRepository.findById(wineId).orElseThrow(
                () -> new ResourceNotFoundException("Wine", "id", String.valueOf(wineId))
        );
        Image image = imageDbRepository.findById(imageId).orElseThrow(
                () -> new ResourceNotFoundException("Image", "id", String.valueOf(imageId))
        );
        if (!image.getWine().getId().equals(wine.getId())) {
            throw new WineApiException(HttpStatus.BAD_REQUEST, "Image does not belong to wine ");
        }
        return image.getContent();
    }

    @Override
    public void deleteById(Long wineId, Long imageId) {
        Wine wine = wineRepository.findById(wineId).orElseThrow(
                () -> new ResourceNotFoundException("Wine", "id", String.valueOf(wineId))
        );
        Image image = imageDbRepository.findById(imageId).orElseThrow(
                () -> new ResourceNotFoundException("Image", "id", String.valueOf(imageId))
        );
        if (!image.getWine().getId().equals(wine.getId())) {
            throw new WineApiException(HttpStatus.BAD_REQUEST, "Image does not belong to wine ");
        }
        imageDbRepository.delete(image);
        log.info("Successfully, delete image for wine by id {}", imageId);
    }
}
