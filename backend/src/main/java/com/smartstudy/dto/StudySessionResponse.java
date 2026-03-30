package com.smartstudy.dto;

import com.smartstudy.entity.SessionFeedback;
import com.smartstudy.entity.SessionStatus;

import java.time.LocalDateTime;

public record StudySessionResponse(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        SessionStatus status,
        SessionFeedback feedback,
        Long subchapterId,
        String subchapterName,
        Long examId,
        String examTitle,
        String courseName
) {
}
