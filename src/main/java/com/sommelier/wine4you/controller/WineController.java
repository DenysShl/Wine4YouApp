package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.WineResponse;
import com.sommelier.wine4you.model.dto.WineRequestDto;
import com.sommelier.wine4you.model.dto.WineResponseDto;
import com.sommelier.wine4you.model.mapper.impl.WineMapperImpl;
import com.sommelier.wine4you.service.ImageService;
import com.sommelier.wine4you.service.WineService;
import com.sommelier.wine4you.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "Rest APIs for Wines resources")
@RestController
@RequestMapping("api/v1/wines")
public class WineController {
    private final WineService wineService;
    private final WineMapperImpl wineMapper;
    private final ImageService imageService;

    @Autowired
    public WineController(WineService wineService,
                          WineMapperImpl wineMapper,
                          ImageService imageService) {
        this.wineService = wineService;
        this.wineMapper = wineMapper;
        this.imageService = imageService;
    }

    @ApiOperation(value = "Create Wine REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<WineResponseDto> create(WineRequestDto wineRequestDto) {
        return new ResponseEntity<>(wineMapper.toDto(wineService.create(wineMapper.toModel(wineRequestDto))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Wines REST API")
    @GetMapping
    public ResponseEntity<WineResponse> getAll(
            @RequestParam(
                    value = "pageNo",
                    defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
                    required = false) int pageNo,
            @RequestParam(
                    value = "pageSize",
                    defaultValue = AppConstants.DEFAULT_PAGE_SIZE_ELEMENT,
                    required = false) int pageSize,
            @RequestParam(
                    value = "sortBy",
                    defaultValue = AppConstants.DEFAULT_SORT_BY,
                    required = false) String sortBy,
            @RequestParam(
                    value = "sortDir",
                    defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,
                    required = false) String sortDir) {
        return ResponseEntity.ok(wineService.getAll(pageNo, pageSize, sortBy, sortDir));
    }

    @ApiOperation(value = "Get Wine By 'Id' REST API")
    @GetMapping("/{id}")
    public ResponseEntity<WineResponseDto> getWineById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(wineMapper.toDto(wineService.getById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<WineResponseDto> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody WineRequestDto wineRequestDto) {
        return ResponseEntity.ok(wineMapper.toDto(wineService.update(id, wineMapper.toModel(wineRequestDto))));
    }

    @ApiOperation(value = "Delete Wine by 'Id' REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        wineService.deleteById(id);
        return ResponseEntity.ok("Success, deleted wine by id " + id);
    }

    @ApiOperation(value = "Get wine by 'Brand' REST API")
    @GetMapping("/brand")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByBrand(
            @RequestParam(value = "brand") String brand) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getAllByBrand(brand).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @ApiOperation(value = "Get wine by 'Name wine' REST API")
    @GetMapping("/nameWine")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByNameWine(
            @RequestParam(value = "nameWine") String nameWine) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getAllByName(nameWine).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @ApiOperation(value = "Get wine by 'Price' REST API")
    @GetMapping("/between-price")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByPrice(
            @RequestParam(value = "min") BigDecimal min,
            @RequestParam(value = "max") BigDecimal max) {
        return ResponseEntity.ok(wineService.getWinesByPriceBetween(min, max).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Get wine by 'Country' REST API")
    @GetMapping("/country")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByCountry(
            @RequestParam(value = "country") String country) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getByCountry(country).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @ApiOperation(value = "Get wine by 'Event' REST API")
    @GetMapping("/event")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByEvent(
            @RequestParam(value = "event") String event) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getByEvent(event).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @ApiOperation(value = "Get wine by 'Type' REST API")
    @GetMapping("/type")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByType(
            @RequestParam(value = "type") String type) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getByWineType(type).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @ApiOperation(value = "Get wine by 'Taste' REST API")
    @GetMapping("/taste")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByTaste(
            @RequestParam(value = "taste") String taste) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getByWineTaste(taste).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @ApiOperation(value = "Get wine by 'Taste' REST API")
    @GetMapping("/style")
    public ResponseEntity<List<WineResponseDto>> getAllWinesByStyle(
            @RequestParam(value = "style") String style) {
        List<WineResponseDto> wineResponseDtos
                = wineService.getByWineTaste(style).stream()
                .map(wine -> wineMapper.toDto(wine))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wineResponseDtos);
    }

    @PostMapping("{wineId}/images")
    public ResponseEntity<?> uploadImage(@PathVariable Long wineId,
                                         @RequestParam("image") MultipartFile multipartImage) {
        return new ResponseEntity<>(imageService.create(wineId, multipartImage), HttpStatus.CREATED);
    }

    @GetMapping(value = "{wineId}/images/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadImage(@PathVariable Long wineId, @PathVariable Long imageId) {
        return ResponseEntity.ok(new ByteArrayResource(imageService.getById(wineId, imageId)));
    }
}
