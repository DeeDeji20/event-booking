package com.musala.eventBooking.dtos.response;

import com.musala.eventBooking.models.enums.Category;
import com.musala.eventBooking.models.enums.EventStatus;
import com.musala.eventBooking.models.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventReservationResponse {
    private Long reservationId;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private Category category;
    private EventStatus eventStatus;
    private ReservationStatus reservationStatus;
}
