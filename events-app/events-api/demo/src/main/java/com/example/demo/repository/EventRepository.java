package com.example.demo.repository;

import com.example.demo.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e join e.participants p where p.id = :id")
    Set<Event> getAllEventsForUser(@Param("id") Long id);
}
