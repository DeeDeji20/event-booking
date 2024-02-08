package com.musala.eventBooking.dtos.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class EventResponse {
    private Long id;
    private String name;
    private LocalDateTime eventDate;
}
