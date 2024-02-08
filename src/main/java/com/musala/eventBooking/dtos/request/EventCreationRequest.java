package com.musala.eventBooking.dtos.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EventCreationRequest {
    private String name;
    private LocalDateTime date;
    private Integer availableAttendeesCount;
    private String description;
    private String category;
}
