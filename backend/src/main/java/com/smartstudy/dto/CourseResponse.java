package com.smartstudy.dto;

import java.time.LocalTime;

public record CourseResponse(
        Long id,
        String name,
        String description,
        Integer minSessionMinutes,
        Integer maxSessionMinutes,
        Integer breakMinutes,
        LocalTime preferredStartTime,
        LocalTime preferredEndTime,
        double progress,
        int chapterCount
) {
}
