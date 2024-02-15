package com.musala.repositories;

import com.musala.models.enums.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.musala.models.enums.EventStatus.ENDED;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;


    @Test
    @Sql(scripts = "/db/data.sql")
    public void testFindByNameLikeTest(){
        LocalDateTime startDate = LocalDateTime.of(2024, 3, 15, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 3, 16, 10, 0, 0);
        var events = eventRepository.findByNameLikeAndEventDateBetweenAndCategory("dev games", startDate , endDate, Category.GAME, PageRequest.of(0, 10));
        assertThat(events).isNotNull();
        assertThat(events.getContent()).isNotEmpty();
    }

    @Test
    public void updateEventsByEventDate() {
        LocalDate date = LocalDate.of(2024, 1, 18);
        eventRepository.updateEventsByEventDate(date, ENDED);
        eventRepository.findEventByEventDate(date).forEach(event -> assertThat(event.getEventStatus()).isEqualTo(ENDED));
    }
}