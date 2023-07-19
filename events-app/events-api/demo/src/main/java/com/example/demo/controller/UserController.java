package com.example.demo.controller;

import com.example.demo.entities.Event;

import com.example.demo.entities.User;
import com.example.demo.service.EventService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}/events")
    public ResponseEntity<Set<Event>> getAllEventsForUser(@PathVariable Long id){
        return new ResponseEntity<>(eventService.getAllEventsForUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> optionalUser = userService.findUserById(id);
        return optionalUser.isPresent() ? new ResponseEntity<>(optionalUser.get(), HttpStatus.OK) : null;
    }
}
