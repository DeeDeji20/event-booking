package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
