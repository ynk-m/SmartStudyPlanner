package com.smartstudy.dto;

import java.time.LocalDateTime;

public record CalendarEventResponse(
        Long id,
        String googleEventId,
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime lastSyncedAt
) {
}
