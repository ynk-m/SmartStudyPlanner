package com.smartstudy.service.sync;

import com.smartstudy.dto.CalendarEventResponse;
import com.smartstudy.entity.CalendarEvent;
import com.smartstudy.repository.CalendarEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarSyncService {

    private static final Logger log = LoggerFactory.getLogger(CalendarSyncService.class);

    private final CalendarEventRepository calendarEventRepository;
    // TODO: inject Google Calendar API client when configured

    public CalendarSyncService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Transactional(readOnly = true)
    public List<CalendarEventResponse> getEvents(LocalDateTime from, LocalDateTime to) {
        return calendarEventRepository.findByStartTimeBetween(from, to).stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Periodic sync - runs every 15 minutes.
     */
    @Scheduled(fixedDelayString = "${app.calendar.sync-interval-ms:900000}")
    public void periodicSync() {
        log.info("Starting periodic Google Calendar sync");
        syncFromGoogle();
    }

    /**
     * On-demand sync triggered by API call.
     */
    @Transactional
    public void syncFromGoogle() {
        // TODO: Implement Google Calendar API integration
        // 1. Fetch events from Google Calendar API within lookahead window
        // 2. For each event, upsert by googleEventId
        // 3. Remove events that no longer exist in Google Calendar
        log.info("Google Calendar sync completed (stub - awaiting API config)");
    }

    @Transactional
    public CalendarEvent upsertEvent(String googleEventId, String title,
                                     LocalDateTime startTime, LocalDateTime endTime) {
        CalendarEvent event = calendarEventRepository.findByGoogleEventId(googleEventId)
                .orElseGet(() -> {
                    CalendarEvent e = new CalendarEvent();
                    e.setGoogleEventId(googleEventId);
                    return e;
                });

        event.setTitle(title);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setLastSyncedAt(LocalDateTime.now());

        return calendarEventRepository.save(event);
    }

    private CalendarEventResponse toResponse(CalendarEvent event) {
        return new CalendarEventResponse(
                event.getId(),
                event.getGoogleEventId(),
                event.getTitle(),
                event.getStartTime(),
                event.getEndTime(),
                event.getLastSyncedAt()
        );
    }
}
