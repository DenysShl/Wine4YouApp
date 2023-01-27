package com.sommelier.wine4you.controller;

import com.sommelier.wine4you.model.dto.event.EventRequestDto;
import com.sommelier.wine4you.model.dto.event.EventResponseDto;
import com.sommelier.wine4you.model.mapper.impl.EventMapperImpl;
import com.sommelier.wine4you.service.EventService;
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
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;
    private final EventMapperImpl eventMapper;

    @Autowired
    public EventController(EventService eventService, EventMapperImpl eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @ApiOperation(value = "Create Event REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EventResponseDto> create(
            @Valid @RequestBody EventRequestDto eventRequestDto) {
        return new ResponseEntity<>(eventMapper.toDto(
                eventService.create(eventMapper.toModel(eventRequestDto))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Event By 'Id' REST API")
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventMapper.toDto(eventService.getById(id)));
    }

    @ApiOperation(value = "Get All Events REST API")
    @GetMapping()
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAll()
                .stream()
                .map(eventMapper::toDto)
                .toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Event By 'Id' REST API")
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateById(
            @PathVariable("id") Long id,
            @Valid @RequestBody EventRequestDto eventRequestDto) {
        return ResponseEntity.ok(eventMapper.toDto(
                eventService.update(id, eventMapper.toModel(eventRequestDto))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Event by 'Id' REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        eventService.deleteById(id);
        return ResponseEntity.ok("Success, deleted event by id " + id);
    }

    @ApiOperation("Get event by 'Name' REST API")
    @GetMapping("/by-name")
    public ResponseEntity<EventResponseDto> getByName(
            @RequestParam(defaultValue = "Birthday", value = "name") String eventName) {
        return ResponseEntity.ok(
                eventMapper.toDto(eventService.findByName(eventName)));
    }
}
