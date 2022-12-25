package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.model.dto.EventRequestDto;
import com.sommelier.wine4you.model.dto.EventResponseDto;
import com.sommelier.wine4you.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements GenericMapper<EventResponseDto, Event, EventRequestDto> {
    @Override
    public EventResponseDto toDto(Event event) {
        EventResponseDto responseDto = new EventResponseDto();
        responseDto.setId(event.getId());
        responseDto.setNameEvent(event.getNameEvent());
        return responseDto;
    }

    @Override
    public Event toModel(EventRequestDto eventRequestDto) {
        Event event = new Event();
        event.setNameEvent(eventRequestDto.getNameEvent());
        return event;
    }
}
