package com.example.demo.service;


import com.example.demo.entities.Event;
import com.example.demo.entities.User;
import com.example.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Set<Event> getAllEventsForUser(Long id){
        return eventRepository.getAllEventsForUser(id);
    }

    public Event saveEvent(Event event){
        event = eventRepository.save(event);
        return event;
    }

    public Event addUserOnEvent(Long eventId, Long userId){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Optional<User> optionalUser = userService.findUserById(userId);
        if(optionalEvent.isEmpty() || optionalUser.isEmpty()){
            return null;
            //TODO change the null return with exception handling
        }
        optionalEvent.get().addUserOnEvent(optionalUser.get());
        return eventRepository.save(optionalEvent.get());
    }

    public Event getEventById(Long id){
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.isPresent()? optionalEvent.get() : null;// TODO to add the EventNotFoundException
    }

    public String deleteEventById(Long id){
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isPresent()) {
            eventRepository.deleteById(id);
            return "Event with the id "+id+" successfully deleted ";
        }
        return null;// TODO to add the EventNotFoundException
    }

    public Event updateEvent(Long id, Event eventJson){
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isPresent()){
            copyEvent(optionalEvent.get(), eventJson);
            return eventRepository.save(optionalEvent.get());
        }
        return null;// TODO to add the EventNotFoundException
    }

    private void copyEvent(Event eventDb, Event eventJson) {
        eventDb.setEventIdentifier(eventJson.getEventIdentifier());
        eventDb.setTitle(eventJson.getTitle());
        eventDb.setDescription(eventJson.getDescription());
        eventDb.setOrganizedBy(eventJson.getOrganizedBy());
        eventDb.setStartDate(eventJson.getStartDate());
        eventDb.setEndDate(eventJson.getEndDate());
        eventDb.setLocation(eventJson.getLocation());
        eventDb.setNumberOfSeats(eventJson.getNumberOfSeats());
    }

}
