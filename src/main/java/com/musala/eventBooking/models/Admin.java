package com.musala.eventBooking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
