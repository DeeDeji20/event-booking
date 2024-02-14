package com.musala.repositories;

import com.musala.models.Event;
import com.musala.models.enums.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Sql(scripts = "/db/data.sql")
    @Test
    public void findEventByEventTest() {
        List<Event> events = eventRepository.findByNameLike("games", null, null);
        assertThat(events.size()).isEqualTo(1);
    }

    @Sql(scripts = "/db/data.sql")
    @Test
    public void findEventByCategoryTest() {
        List<Event> events = eventRepository.findByNameLike(null, Category.CONFERENCE, null);
        assertThat(events.size()).isEqualTo(1);
    }

    @Sql(scripts = "/db/data.sql")
    @Test
    public void findEventByStartDateTest() {
        List<Event> events = eventRepository.findByNameLike(null, null, LocalDate.of(2024, 2, 15));
        assertThat(events.size()).isEqualTo(1);
    }


    @Sql(scripts = "/db/data.sql")
    @Test
    void findByNameEventDateBetweenTest() {
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 25, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 26, 10, 0, 0);
        var events = eventRepository.findByNameLikeAndEventDateBetweenAndCategory(null, startDate, endDate, null, null);
        assertThat(events.getContent()).isNotEmpty();
    }

    @Test
    @Sql(scripts = "/db/data.sql")
    public void testFindByNameLikeTest(){
        var events = eventRepository.findByNameLikeAndEventDateBetweenAndCategory("dev zone", null, null, null, null);
        assertThat(events).isNotNull();
        assertThat(events.getContent()).isNotEmpty();
    }
}