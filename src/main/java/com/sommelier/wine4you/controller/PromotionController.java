package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.wine.WineResponseDto;
import com.sommelier.wine4you.model.enums.WineType;
import com.sommelier.wine4you.model.mapper.impl.WineMapperImpl;
import com.sommelier.wine4you.service.WineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Rest API for product promotion")
@RestController
@RequestMapping("api/v1/promotions")
public class PromotionController {
    private static final int PROMOTION_PRODUCTS_QUANTITY = 4;
    private final WineService wineService;
    private final WineMapperImpl wineMapper;

    public PromotionController(WineService wineService,
                               WineMapperImpl wineMapper) {
        this.wineService = wineService;
        this.wineMapper = wineMapper;
    }

    @ApiOperation(value = "Get wine promotion of the week REST API")
    @GetMapping("/wines")
    public ResponseEntity<Set<WineResponseDto>> getWinePromotion() {
        return ResponseEntity.ok(wineService.getAll()
                .stream()
                .filter(wine -> !wine.getWineType()
                        .equals(WineType.CHAMPAGNE_SPARKLING))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(ArrayList::new),
                        list -> {
                            Collections.shuffle(list);
                            return list;
                        }))
                .stream()
                .limit(PROMOTION_PRODUCTS_QUANTITY)
                .map(wineMapper::toDto)
                .collect(Collectors.toSet()));
    }

    @ApiOperation(value = "Get champagne promotion of the week REST API")
    @GetMapping("/champagnes")
    public ResponseEntity<Set<WineResponseDto>> getChampagnePromotion() {
        return ResponseEntity.ok(wineService.getAll()
                .stream()
                .filter(wine -> wine.getWineType()
                        .equals(WineType.CHAMPAGNE_SPARKLING))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(ArrayList::new),
                        list -> {
                            Collections.shuffle(list);
                            return list;
                        }))
                .stream()
                .limit(PROMOTION_PRODUCTS_QUANTITY)
                .map(wineMapper::toDto)
                .collect(Collectors.toSet()));
    }
}
