package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Event;
import java.util.List;

public interface EventService {
    Event create(Event event);
    Event findById(Long id);
    List<Event> getAll();
    void deleteById(Long id);
    Event findByEventName(String name);
}
