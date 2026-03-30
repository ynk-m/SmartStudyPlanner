package com.smartstudy.dto;

import java.util.List;

public record ChapterResponse(
        Long id,
        String name,
        Long courseId,
        double progress,
        List<SubchapterResponse> subchapters
) {
}
