package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.repository.EventRepository;
import com.sommelier.wine4you.service.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event", "id", String.valueOf(id)));
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        eventRepository.deleteById(id);
        return eventRepository.existsById(id);
    }

    @Override
    public Event findByName(String name) {
        return eventRepository.findByNameEvent(name).orElseThrow(
                () -> new ResourceNotFoundException("Event", "Name", name));
    }

    @Override
    public Event update(Long id, Event event) {
        event.setId(id);
        return eventRepository.save(event);
    }
}
