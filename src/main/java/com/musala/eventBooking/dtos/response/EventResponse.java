package com.musala.eventBooking.dtos.response;

import com.musala.eventBooking.models.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class EventResponse {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDateTime eventDate;
}
