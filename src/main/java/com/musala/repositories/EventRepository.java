package com.musala.repositories;

import com.musala.models.Event;
import com.musala.models.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
//    @Query("SELECT e FROM Event e WHERE e.name = :name OR e.category = :category OR e.eventDate BETWEEN :startDate AND :endDate")
    Page<Event> findByNameLikeAndEventDateBetweenAndCategory(String name, LocalDateTime startDate, LocalDateTime endDate, Category category, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.name LIKE CONCAT('%', :name, '%') AND e.category = :category AND CAST(e.eventDate AS LOCALDATE) = :localDate ")
    List<Event> findByNameLike(String name, Category category, LocalDate localDate);

    @Query("select e from Event e WHERE DATE(e.eventDate) = DATE(:date)")
    List<Event> findEventByEventDate(LocalDate date);

}
