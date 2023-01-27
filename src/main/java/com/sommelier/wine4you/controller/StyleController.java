package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.style.WineStyleRequestDto;
import com.sommelier.wine4you.model.dto.style.WineStyleResponseDto;
import com.sommelier.wine4you.model.mapper.impl.WineStyleMapperImpl;
import com.sommelier.wine4you.service.WineStyleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@Api(value = "Rest APIs for Wine styles resources")
@RestController
@RequestMapping("api/v1/styles")
public class StyleController {
    private final WineStyleService styleService;
    private final WineStyleMapperImpl styleMapper;

    @Autowired
    public StyleController(WineStyleService styleService,
                           WineStyleMapperImpl styleMapper) {
        this.styleService = styleService;
        this.styleMapper = styleMapper;
    }

    @ApiOperation(value = "Create Style REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<WineStyleResponseDto> create(
            @Valid @RequestBody WineStyleRequestDto styleRequestDto) {
        return new ResponseEntity<>(styleMapper.toDto(
                styleService.create(styleMapper.toModel(styleRequestDto))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Style By 'Id' REST API")
    @GetMapping("/{id}")
    public ResponseEntity<WineStyleResponseDto> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(styleMapper.toDto(styleService.getById(id)));
    }

    @ApiOperation(value = "Get All Styles REST API")
    @GetMapping()
    public ResponseEntity<List<WineStyleResponseDto>> getAllEvents() {
        return ResponseEntity.ok(styleService.getAll()
                .stream()
                .map(styleMapper::toDto)
                .toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Style By 'Id' REST API")
    @PutMapping("/{id}")
    public ResponseEntity<WineStyleResponseDto> updateById(
            @PathVariable("id") Long id,
            @Valid @RequestBody WineStyleRequestDto styleRequestDto) {
        return ResponseEntity.ok(styleMapper.toDto(
                styleService.update(id, styleMapper.toModel(styleRequestDto))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Style by 'Id' REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        styleService.deleteById(id);
        return ResponseEntity.ok("Success, deleted event by id " + id);
    }

    @ApiOperation("Get style by 'Name' REST API")
    @GetMapping("/by-name")
    public ResponseEntity<WineStyleResponseDto> getByName(
            @RequestParam(defaultValue = "Semi Sweet", value = "name") String styleName) {
        return ResponseEntity.ok(
                styleMapper.toDto(styleService.findByName(styleName)));
    }
}
