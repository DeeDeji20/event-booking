package com.musala.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.musala.models.enums.Category;
import com.musala.models.enums.EventStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse implements Serializable {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private UserResponse createdBy;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime eventDate;
    private Integer availableAttendeesCount;
    private Integer currentNumberOfAttendees;
    private EventStatus eventStatus;
}
