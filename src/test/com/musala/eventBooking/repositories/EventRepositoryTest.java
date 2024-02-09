package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindEventById(){
        System.out.println(eventRepository.findById(1L));
    }

    @Test
    void testFindAvailableEventsByCriteria_findByCategory(){
        Page<Event> events = eventRepository.findEventByParameter("GAME",PageRequest.of(0,15, Sort.Direction.DESC, "id"));
        System.out.println(events.stream().findFirst());
        assertThat(events.getTotalElements()).isEqualTo(1L);
    }

    @Test
    void testFindAvailableEventsByCriteria_findByName(){
        Page<Event> events = eventRepository.findEventByParameter("Game time", PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        System.out.println(events.stream().findFirst());
        assertThat(events.getTotalElements()).isEqualTo(1L);
    }

    @Test
    void testFindAvailableEventsByCriteria_findByEventDate(){
        Page<Event> events = eventRepository.findEventByParameter("2024-01-06",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        System.out.println(events.stream().toList());
        assertThat(events.getTotalElements()).isEqualTo(1L);
    }
}
