package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.repository.EventRepository;
import com.sommelier.wine4you.service.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event", "id", String.valueOf(id)));
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event findByEventName(String name) {
        return eventRepository.findByNameEvent(name).orElseThrow(() ->
                new ResourceNotFoundException("Event", "Name", name));
    }
}
