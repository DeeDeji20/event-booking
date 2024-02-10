package com.musala.repositories;

import com.musala.models.Event;
import com.musala.models.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByNameOrEventDateBetweenOrCategory(String name, LocalDateTime startDate, LocalDateTime endDate, Category category, Pageable pageable);
}
