package com.musala.models;

import com.musala.models.enums.Category;
import com.musala.models.enums.EventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Setter
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull
    private LocalDateTime eventDate;
    private int currentNumberOfAttendees;
    private int availableAttendeesCount;
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
