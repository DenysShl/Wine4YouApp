package com.sommelier.wine4you.model.mapper.impl;

import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.model.dto.event.EventRequestDto;
import com.sommelier.wine4you.model.dto.event.EventResponseDto;
import com.sommelier.wine4you.model.mapper.MapperToDto;
import com.sommelier.wine4you.model.mapper.MapperToModel;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements MapperToDto<EventResponseDto, Event>,
        MapperToModel<Event, EventRequestDto> {
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
