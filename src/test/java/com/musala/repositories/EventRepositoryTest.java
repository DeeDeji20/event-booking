package com.musala.repositories;

import com.musala.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void findEventByEventDateTest() {
        List<Event> events = eventRepository.findEventByEventDate(LocalDate.of(2024, 2, 15));
        assertThat(events.size()).isEqualTo(1);
    }
}