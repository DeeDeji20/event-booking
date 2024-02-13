package com.musala.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.models.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private UserResponse createdBy;
    private LocalDateTime eventDate;
//    private int currentNumberOfAttendees;
    private Integer availableAttendeesCount;
}
