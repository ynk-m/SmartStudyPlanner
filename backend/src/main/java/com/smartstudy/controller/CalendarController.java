package com.smartstudy.controller;

import com.smartstudy.dto.CalendarEventResponse;
import com.smartstudy.service.sync.CalendarSyncService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarSyncService calendarSyncService;

    public CalendarController(CalendarSyncService calendarSyncService) {
        this.calendarSyncService = calendarSyncService;
    }

    @GetMapping("/events")
    public List<CalendarEventResponse> getEvents(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return calendarSyncService.getEvents(start, end);
    }

    @PostMapping("/sync")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sync() {
        calendarSyncService.syncFromGoogle();
    }
}
