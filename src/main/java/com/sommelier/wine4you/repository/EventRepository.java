package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Event;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByNameEvent(String nameEvent);
}
