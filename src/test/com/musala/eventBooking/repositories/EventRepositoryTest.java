package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    void testFindByCriteria_findByCategory(){
        Pageable pageable = PageRequest.of(1,15);
        Page<Event> events = eventRepository.findByCategoryOrAndEventDateOrName("GAME", pageable);
        assertThat(events.getTotalElements()).isEqualTo(1L);
    }

    @Test
    void testFindByCriteria_findByName(){
        Pageable pageable = PageRequest.of(1,15);
        Page<Event> events = eventRepository.findByCategoryOrAndEventDateOrName("Game time", pageable);
        assertThat(events.getTotalElements()).isEqualTo(1L);
    }

    @Test
    void testFindByCriteria_findByEventDate(){
        Pageable pageable = PageRequest.of(1,15);
        Page<Event> events = eventRepository.findByCategoryOrAndEventDateOrName("2024-01-15", pageable);
        assertThat(events.getTotalElements()).isEqualTo(1L);
    }
}
