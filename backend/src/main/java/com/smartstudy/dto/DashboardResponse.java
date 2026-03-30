package com.smartstudy.dto;

import java.util.List;

public record DashboardResponse(
        List<ExamResponse> upcomingExams,
        List<StudySessionResponse> todaySessions,
        double weeklyStudyHours,
        int completedSessionsThisWeek,
        int totalScheduledSessionsThisWeek
) {
}
