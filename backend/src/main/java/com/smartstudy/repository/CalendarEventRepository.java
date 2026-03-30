package com.smartstudy.repository;

import com.smartstudy.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {

    Optional<CalendarEvent> findByGoogleEventId(String googleEventId);

    List<CalendarEvent> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
