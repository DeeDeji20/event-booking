package com.musala.eventBooking.dtos.response;

import com.musala.eventBooking.models.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDateTime eventDate;
}
