package com.example.demo.controller;

import com.example.demo.entities.Event;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping({"/{id}"})
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return new ResponseEntity(eventService.getAllEvents(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(eventService.deleteEventById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event event){
        return new ResponseEntity<Event>(eventService.saveEvent(event), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event){
        return new ResponseEntity<Event>(eventService.updateEvent(id, event), HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}/add-user/{userId}")
    public ResponseEntity<Event> addUserOnEvent(@PathVariable Long id, @PathVariable Long userId){
        return new ResponseEntity<>(eventService.addUserOnEvent(id, userId),HttpStatus.CREATED);
    }
}
