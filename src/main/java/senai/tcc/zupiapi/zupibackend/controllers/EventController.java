package senai.tcc.zupiapi.zupibackend.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import senai.tcc.zupiapi.zupibackend.model.Event;
import senai.tcc.zupiapi.zupibackend.services.EventService;

import java.net.URI;
import java.util.List;
    
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/events")
public class    EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        List<Event> events = eventService.findAll();

        return ResponseEntity.ok().body(events);
    }

    @PostMapping
    public ResponseEntity<Event> add(@RequestBody Event event) {
        Event newEvent = eventService.add(event);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEvent.getId()).toUri();

        return ResponseEntity.created(uri).body(newEvent);
    }
}
