package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image create(Long wineId, MultipartFile multipartImage);

    byte[] getById(Long wineId, Long imageId);

    void deleteById(Long wineId, Long imageId);
}
