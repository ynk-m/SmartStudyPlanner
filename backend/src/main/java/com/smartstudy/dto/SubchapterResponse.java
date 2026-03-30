package com.smartstudy.dto;

import java.time.LocalDateTime;

public record SubchapterResponse(
        Long id,
        String name,
        Long chapterId,
        Integer estimatedMinutes,
        Double readinessScore,
        LocalDateTime nextReviewAt
) {
}
