package com.musala.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.musala.models.enums.Category;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EventCreationRequest {
    @NotBlank
    @Size(max = 100)
    private String name;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    @NotNull
    @Positive
    @Max(1000)
    private int availableAttendeesCount;
    @NotBlank
    @Size(max = 500)
    private String description;
    @NotNull
    private String category;
}
