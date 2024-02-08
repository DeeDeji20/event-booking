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

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100)
    private String name;
    @NotNull
    private LocalDateTime date;
    @Positive
    @Max(1000)
    private int availableAttendeesCount;
    @Size(max = 500)
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
}
