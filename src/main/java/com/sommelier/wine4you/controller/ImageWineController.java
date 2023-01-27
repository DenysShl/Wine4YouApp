package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.Image;
import com.sommelier.wine4you.model.dto.image.ImageWineResponseDto;
import com.sommelier.wine4you.model.mapper.impl.ImageMapperImpl;
import com.sommelier.wine4you.service.ImageService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/wines")
public class ImageWineController {
    private final ImageService imageService;
    private final ImageMapperImpl imageMapper;

    @Autowired
    public ImageWineController(ImageService imageService,
                               ImageMapperImpl imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @ApiOperation(value = "Upload Wine image By 'Id' REST API")
    @PostMapping("{wineId}/images")
    public ResponseEntity<?> uploadImage(@PathVariable Long wineId,
                                         @RequestParam("image") MultipartFile multipartImage) {
        imageService.create(wineId, multipartImage);
        return new ResponseEntity<>("Image uploaded successfully: "
                + multipartImage.getOriginalFilename(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Download Wine image By 'Id' REST API")
    @GetMapping(value = "{wineId}/images/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadImage(@PathVariable Long wineId,
                                           @PathVariable Long imageId) {
        byte[] imageData = new ByteArrayResource(imageService
                .getById(wineId, imageId)).getByteArray();
        return ResponseEntity.ok(imageData);
    }

    @ApiOperation(value = "Download All images By 'wineId' REST API")
    @GetMapping(value = "{wineId}/images")
    public ResponseEntity<List<ImageWineResponseDto>> downloadImage(@PathVariable Long wineId) {
        return ResponseEntity.ok(imageService.getAllByWineId(wineId)
                .stream()
                .map(imageMapper::toDto)
                .toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Wine image By 'Id' REST API")
    @DeleteMapping(value = "{wineId}/images/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> deleteloadImage(@PathVariable Long wineId,
                                             @PathVariable Long imageId) {
        Image image = imageService.getById(imageId);
        imageService.deleteById(wineId, imageId);
        return ResponseEntity.ok("Image deleted successfully: "
                + image.getName());
    }
}
