package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e where LOWER(CAST(e.category AS STRING)) = LOWER(:criteria) " +
            "OR LOWER(e.name) LIKE LOWER(CONCAT('%', :criteria, '%')) " +
            "OR CAST(e.eventDate AS string) LIKE CONCAT('%', :criteria, '%') AND CAST(e.eventStatus AS STRING ) != 'ENDED' ")

    Page<Event> findEventByParameter(String criteria, PageRequest pageRequest);
}
