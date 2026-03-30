package com.smartstudy.service;

import com.smartstudy.dto.DashboardResponse;
import com.smartstudy.dto.ExamResponse;
import com.smartstudy.dto.StudySessionResponse;
import com.smartstudy.entity.SessionStatus;
import com.smartstudy.entity.StudySession;
import com.smartstudy.repository.StudySessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
public class DashboardService {

    private final ExamService examService;
    private final StudySessionService sessionService;
    private final StudySessionRepository sessionRepository;

    public DashboardService(ExamService examService,
                            StudySessionService sessionService,
                            StudySessionRepository sessionRepository) {
        this.examService = examService;
        this.sessionService = sessionService;
        this.sessionRepository = sessionRepository;
    }

    @Transactional(readOnly = true)
    public DashboardResponse getDashboard() {
        List<ExamResponse> upcomingExams = examService.findAll().stream()
                .filter(e -> e.daysRemaining() > 0)
                .toList();

        LocalDate today = LocalDate.now();
        LocalDateTime dayStart = today.atStartOfDay();
        LocalDateTime dayEnd = today.atTime(LocalTime.MAX);

        List<StudySessionResponse> todaySessions = sessionService.findByDateRange(dayStart, dayEnd);

        // Weekly stats
        LocalDateTime weekStart = today.with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime weekEnd = today.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);

        List<StudySession> weekSessions = sessionRepository.findByStartTimeBetween(weekStart, weekEnd);

        long completedCount = weekSessions.stream()
                .filter(s -> s.getStatus() == SessionStatus.COMPLETED)
                .count();

        double weeklyHours = weekSessions.stream()
                .filter(s -> s.getStatus() == SessionStatus.COMPLETED)
                .mapToDouble(s -> Duration.between(s.getStartTime(), s.getEndTime()).toMinutes() / 60.0)
                .sum();

        return new DashboardResponse(
                upcomingExams,
                todaySessions,
                weeklyHours,
                (int) completedCount,
                weekSessions.size()
        );
    }
}
