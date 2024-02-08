package com.musala.eventBooking.models;

import com.musala.eventBooking.models.enums.Category;
import com.musala.eventBooking.models.enums.EventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Size(max = 100)
    private String name;
    @NotNull
    private LocalDateTime eventDate;
//    @Positive
//    @Max(1000)
    private int availableAttendeesCount;
//    @Size(max = 500)
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private User createdBy;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
    private int declinedCount;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
