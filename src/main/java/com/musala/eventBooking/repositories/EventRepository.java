package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e where e.category = CAST(:criteria AS STRING) " +
            "OR e.name LIKE CONCAT('%', :criteria, '%') " +
            "OR CAST(e.eventDate AS string) LIKE CONCAT('%', :criteria, '%')")
    Page<Event> findByCategoryOrAndEventDateOrName(String criteria, Pageable pageable);
}
