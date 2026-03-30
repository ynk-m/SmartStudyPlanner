package com.smartstudy.dto;

import java.time.LocalDateTime;

public record ExamResponse(
        Long id,
        String title,
        LocalDateTime examDate,
        String notes,
        Long courseId,
        String courseName,
        long daysRemaining,
        double progress,
        int sessionCount
) {
}
