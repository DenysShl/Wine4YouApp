package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.taste.WineTasteRequestDto;
import com.sommelier.wine4you.model.dto.taste.WineTasteResponseDto;
import com.sommelier.wine4you.model.mapper.impl.WineTasteMapperImpl;
import com.sommelier.wine4you.service.WineTasteService;
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

@Api(value = "Rest APIs for Wine events resources")
@RestController
@RequestMapping("api/v1/tastes")
public class TasteController {
    private final WineTasteService tasteService;
    private final WineTasteMapperImpl tasteMapper;

    @Autowired
    public TasteController(WineTasteService tasteService,
                           WineTasteMapperImpl tasteMapper) {
        this.tasteService = tasteService;
        this.tasteMapper = tasteMapper;
    }

    @ApiOperation(value = "Create Taste REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<WineTasteResponseDto> create(
            @Valid @RequestBody WineTasteRequestDto tasteRequestDto) {
        return new ResponseEntity<>(tasteMapper.toDto(
                tasteService.create(tasteMapper.toModel(tasteRequestDto))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Taste By 'Id' REST API")
    @GetMapping("/{id}")
    public ResponseEntity<WineTasteResponseDto> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(tasteMapper.toDto(tasteService.getById(id)));
    }

    @ApiOperation(value = "Get All Tastes REST API")
    @GetMapping()
    public ResponseEntity<List<WineTasteResponseDto>> getAllEvents() {
        return ResponseEntity.ok(tasteService.getAll()
                .stream()
                .map(tasteMapper::toDto)
                .toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Taste By 'Id' REST API")
    @PutMapping("/{id}")
    public ResponseEntity<WineTasteResponseDto> updateById(
            @PathVariable("id") Long id,
            @Valid @RequestBody WineTasteRequestDto tasteRequestDto) {
        return ResponseEntity.ok(tasteMapper.toDto(
                tasteService.update(id, tasteMapper.toModel(tasteRequestDto))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Taste by 'Id' REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        tasteService.deleteById(id);
        return ResponseEntity.ok("Success, deleted taste by id " + id);
    }

    @ApiOperation("Get taste by 'Name' REST API")
    @GetMapping("/by-name")
    public ResponseEntity<WineTasteResponseDto> getByName(
            @RequestParam(defaultValue = "Tropical", value = "name") String tasteName) {
        return ResponseEntity.ok(
                tasteMapper.toDto(tasteService.findByName(tasteName)));
    }
}
