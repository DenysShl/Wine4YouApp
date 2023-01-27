package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Image;
import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.repository.ImageDbRepository;
import com.sommelier.wine4you.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {
    private final ImageDbRepository imageDbRepository;
    private final WineRepository wineRepository;

    @Autowired
    public ImageController(ImageDbRepository imageDbRepository, WineRepository wineRepository) {
        this.imageDbRepository = imageDbRepository;
        this.wineRepository = wineRepository;
    }

    @PostMapping
    Long uploadImage(@RequestParam MultipartFile multipartImage) throws Exception {
        Wine wine = wineRepository.findById(1L).orElseThrow(
                () -> new ResourceNotFoundException("Wine", "id", String.valueOf(1))
        );
        Image dbImage = new Image();
        dbImage.setWine(wine);
        dbImage.setName(multipartImage.getName());
        dbImage.setType(multipartImage.getContentType());
        dbImage.setContent(multipartImage.getBytes());
        return imageDbRepository.save(dbImage).getId();
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long imageId) {
        byte[] image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }
}
