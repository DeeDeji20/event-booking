package com.musala.dtos.response;

import com.musala.models.enums.ReservationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ReservationResponse {
    private Long id;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private LocalDateTime createdAt;
}
