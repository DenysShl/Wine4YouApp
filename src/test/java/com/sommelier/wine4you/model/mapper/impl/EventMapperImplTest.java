package com.sommelier.wine4you.model.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.model.dto.event.EventRequestDto;
import com.sommelier.wine4you.model.dto.event.EventResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EventMapperImplTest {
    private static EventMapperImpl eventMapper;
    private static Event event;
    private static Event eventTest;
    private static EventResponseDto responseDto;
    private static EventRequestDto requestDto;

    @BeforeAll
    static void setUp() {
        eventMapper = new EventMapperImpl();
        event = new Event();
        event.setId(1L);
        event.setNameEvent("Marriage");

        responseDto = new EventResponseDto();
        responseDto.setId(1L);
        responseDto.setNameEvent("Marriage");

        requestDto = new EventRequestDto();
        requestDto.setNameEvent("Party");

        eventTest = new Event();
        eventTest.setNameEvent("Party");

    }

    @Test
    void toDto_Ok() {
        EventResponseDto actual = eventMapper.toDto(event);
        assertNotNull(actual);
        assertEquals(responseDto, actual);
        assertEquals(responseDto.getId(), actual.getId());
        assertEquals(responseDto.getNameEvent(), actual.getNameEvent());
    }

    @Test
    void toModel_Ok() {
        Event actual = eventMapper.toModel(requestDto);
        assertNotNull(actual);
        assertEquals(eventTest.getNameEvent(), actual.getNameEvent());
    }
}
