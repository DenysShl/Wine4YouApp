package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Event;

public interface EventService extends GenericService<Event> {
    Event findByName(String name);
}
