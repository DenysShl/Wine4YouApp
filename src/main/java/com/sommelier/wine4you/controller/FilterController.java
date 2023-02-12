package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.wine.WineRequestFilterDto;
import com.sommelier.wine4you.model.dto.wine.WineResponseDto;
import com.sommelier.wine4you.model.mapper.impl.WineMapperImpl;
import com.sommelier.wine4you.service.CustomWineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Rest API for filter conditions")
@RestController
@RequestMapping("api/v1/filters")
public class FilterController {
    private final CustomWineService customWineService;
    private final WineMapperImpl wineMapper;

    public FilterController(CustomWineService customWineService,
                            WineMapperImpl wineMapper) {
        this.customWineService = customWineService;
        this.wineMapper = wineMapper;
    }

    @ApiOperation(value = "Get Wine by 'Predicates' Rest API")
    @GetMapping
    public ResponseEntity<List<WineResponseDto>> getWinesByCriteriaBuilder(
            @Valid @RequestBody WineRequestFilterDto wineRequestFilterDto) {
        return ResponseEntity.ok(customWineService.getWinesByCriteria(wineRequestFilterDto)
                        .stream()
                        .map(wineMapper::toDto)
                        .toList()
        );
    }
}
