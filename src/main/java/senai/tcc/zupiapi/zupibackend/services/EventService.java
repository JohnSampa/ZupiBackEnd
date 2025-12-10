package senai.tcc.zupiapi.zupibackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.tcc.zupiapi.zupibackend.dto.EventDTO;
import senai.tcc.zupiapi.zupibackend.model.Child;
import senai.tcc.zupiapi.zupibackend.model.Event;
import senai.tcc.zupiapi.zupibackend.model.SkillArea;
import senai.tcc.zupiapi.zupibackend.model.User;
import senai.tcc.zupiapi.zupibackend.repositories.ChildRepository;
import senai.tcc.zupiapi.zupibackend.repositories.EventRepository;
import senai.tcc.zupiapi.zupibackend.repositories.SkillAreaRepository;
import senai.tcc.zupiapi.zupibackend.repositories.UserRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> findAll(Long id) {
        return eventRepository.findAllByUserId(id);
    }

    public Event add(EventDTO event) {
        Event newEvent = new Event();

        newEvent.setTitle(event.title());
        newEvent.setDate(event.date());
        newEvent.setFinish(event.finish());

        SkillArea newSkillArea = skillAreaRepository.findById(event.skillArea()).orElse(null);
        newEvent.setSkillArea(newSkillArea);
        Child newChild = childRepository.findById(event.childId()).orElse(null);
        newEvent.setChild(newChild);
        User newUser = userRepository.findById(event.userId()).orElse(null);
        return eventRepository.save(newEvent);
    }
}
