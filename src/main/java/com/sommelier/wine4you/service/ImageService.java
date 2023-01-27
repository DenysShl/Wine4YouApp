package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Image;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image create(Long wineId, MultipartFile multipartImage);

    byte[] getById(Long wineId, Long imageId);

    Image getById(Long id);

    boolean deleteById(Long wineId, Long imageId);

    List<Image> getAllByWineId(Long wineId);
}
