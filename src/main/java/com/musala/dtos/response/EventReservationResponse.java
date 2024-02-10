package com.musala.dtos.response;

import com.musala.models.enums.Category;
import com.musala.models.enums.EventStatus;
import com.musala.models.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventReservationResponse {
    private Long reservationId;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private Category category;
    private EventStatus eventStatus;
    private ReservationStatus reservationStatus;
    private UserResponse user;
}
