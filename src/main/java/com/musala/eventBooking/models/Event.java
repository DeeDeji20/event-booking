package com.musala.eventBooking.models;

import com.musala.eventBooking.models.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100)
    private String name;
    @NotNull
    private Timestamp date;
    @Positive
    @Max(1000)
    private int availableAttendeesCount;
    @Size(max = 500)
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
}
