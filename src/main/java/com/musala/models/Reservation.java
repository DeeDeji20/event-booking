package com.musala.models;

import com.musala.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = {MERGE})
    private Event event;

    private Integer ticketCount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
