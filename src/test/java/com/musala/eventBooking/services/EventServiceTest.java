package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.models.enums.Authority;
import com.musala.eventBooking.services.events.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {
    private EventCreationRequest eventCreationRequest;

    @Autowired
    private EventService eventService;

    @BeforeEach
    void setup(){
        eventCreationRequest = EventCreationRequest.builder()
                .name("John Doe")
                .date(LocalDateTime.now())
                .category("GAME")
                .availableAttendeesCount(100)
                .description("Test event")
                .build();
    }

    @Test
    void createEvent() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@email.com");
        user.setAuthorities(Set.of(Authority.USER));
        EventResponse response = eventService.createEvent(eventCreationRequest, user);
        assertNotNull(response);
    }
}