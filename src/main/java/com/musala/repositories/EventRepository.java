package com.musala.repositories;

import com.musala.models.Event;
import com.musala.models.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e where LOWER(CAST(e.category AS STRING)) = LOWER(:criteria) " +
            "OR LOWER(e.name) LIKE LOWER(CONCAT('%', :criteria, '%')) " +
            "OR CAST(e.eventDate AS string) LIKE CONCAT('%', :criteria, '%') AND CAST(e.eventStatus AS STRING ) != 'ENDED' ")

    Page<Event> findEventByParameter(String criteria, PageRequest pageRequest);

    @Query("SELECT e FROM Event e " +
            "WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR (e.eventDate BETWEEN :startDate AND :endDate) " +
            "OR (e.category = :category) AND e.eventStatus != 'ENDED')"
    )
    Page<Event> findEventByNameLikeOrCategoryOrEventDateBetween(String name, Category category, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
