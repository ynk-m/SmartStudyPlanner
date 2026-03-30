package com.smartstudy.service.scheduler;

import com.smartstudy.entity.*;
import com.smartstudy.repository.CalendarEventRepository;
import com.smartstudy.repository.StudySessionRepository;
import com.smartstudy.repository.SubchapterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.*;

@Service
public class StudySchedulerService {

    private static final double READINESS_THRESHOLD = 0.8;

    private final SubchapterRepository subchapterRepository;
    private final StudySessionRepository sessionRepository;
    private final CalendarEventRepository calendarEventRepository;

    public StudySchedulerService(SubchapterRepository subchapterRepository,
                                 StudySessionRepository sessionRepository,
                                 CalendarEventRepository calendarEventRepository) {
        this.subchapterRepository = subchapterRepository;
        this.sessionRepository = sessionRepository;
        this.calendarEventRepository = calendarEventRepository;
    }

    /**
     * Generates study sessions for an exam, respecting course constraints,
     * existing calendar events, and spaced repetition priorities.
     */
    @Transactional
    public List<StudySession> generateSessions(Exam exam) {
        Course course = exam.getCourse();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime examDate = exam.getExamDate();

        // Remove future scheduled (not completed) sessions for this exam
        List<StudySession> existingFuture = sessionRepository
                .findByExamIdAndStatusAndStartTimeAfter(exam.getId(), SessionStatus.SCHEDULED, now);
        sessionRepository.deleteAll(existingFuture);

        // Get subchapters that need review
        List<Subchapter> subchaptersToReview = subchapterRepository
                .findSubchaptersNeedingReview(course.getId(), READINESS_THRESHOLD, now);

        if (subchaptersToReview.isEmpty()) {
            return Collections.emptyList();
        }

        // Collect busy slots in the range
        List<TimeSlot> busySlots = getBusySlots(now, examDate);

        // Generate free windows per day
        List<StudySession> generated = new ArrayList<>();
        LocalDate startDate = now.toLocalDate().plusDays(1); // start tomorrow
        LocalDate endDate = examDate.toLocalDate();
        long totalDays = Math.max(1, Duration.between(now, examDate).toDays());

        Queue<Subchapter> subchapterQueue = new LinkedList<>(subchaptersToReview);

        for (LocalDate date = startDate; !date.isAfter(endDate) && !subchapterQueue.isEmpty(); date = date.plusDays(1)) {
            List<TimeSlot> freeWindows = getFreeWindows(date, course, busySlots);

            // Increase density as exam approaches (urgency factor)
            long daysUntilExam = Duration.between(date.atStartOfDay(), examDate).toDays();
            double urgencyFactor = 1.0 + (1.0 - (double) daysUntilExam / totalDays);

            for (TimeSlot window : freeWindows) {
                if (subchapterQueue.isEmpty()) break;

                long windowMinutes = Duration.between(window.start, window.end).toMinutes();
                if (windowMinutes < course.getMinSessionMinutes()) continue;

                // Split window into sessions
                LocalDateTime sessionStart = window.start;
                while (sessionStart.plusMinutes(course.getMinSessionMinutes()).isBefore(window.end)
                       || sessionStart.plusMinutes(course.getMinSessionMinutes()).isEqual(window.end)) {

                    if (subchapterQueue.isEmpty()) break;

                    Subchapter sub = subchapterQueue.peek();
                    int sessionLength = Math.min(
                            sub.getEstimatedMinutes(),
                            course.getMaxSessionMinutes()
                    );
                    sessionLength = Math.max(sessionLength, course.getMinSessionMinutes());

                    LocalDateTime sessionEnd = sessionStart.plusMinutes(sessionLength);
                    if (sessionEnd.isAfter(window.end)) break;

                    StudySession session = new StudySession();
                    session.setStartTime(sessionStart);
                    session.setEndTime(sessionEnd);
                    session.setSubchapter(sub);
                    session.setExam(exam);
                    session.setStatus(SessionStatus.SCHEDULED);
                    generated.add(session);

                    subchapterQueue.poll();
                    // Re-add if readiness is low (needs more sessions)
                    if (sub.getReadinessScore() < 0.4) {
                        subchapterQueue.add(sub); // will appear again later
                    }

                    sessionStart = sessionEnd.plusMinutes(course.getBreakMinutes());
                }
            }
        }

        return sessionRepository.saveAll(generated);
    }

    private List<TimeSlot> getBusySlots(LocalDateTime from, LocalDateTime to) {
        List<TimeSlot> busy = new ArrayList<>();

        // Google Calendar events
        calendarEventRepository.findByStartTimeBetween(from, to)
                .forEach(e -> busy.add(new TimeSlot(e.getStartTime(), e.getEndTime())));

        // Existing study sessions (completed or scheduled)
        sessionRepository.findByStartTimeBetween(from, to)
                .forEach(s -> busy.add(new TimeSlot(s.getStartTime(), s.getEndTime())));

        busy.sort(Comparator.comparing(s -> s.start));
        return busy;
    }

    private List<TimeSlot> getFreeWindows(LocalDate date, Course course, List<TimeSlot> busySlots) {
        LocalDateTime dayStart = date.atTime(course.getPreferredStartTime());
        LocalDateTime dayEnd = date.atTime(course.getPreferredEndTime());

        // Filter busy slots for this day
        List<TimeSlot> dayBusy = busySlots.stream()
                .filter(s -> s.start.toLocalDate().equals(date) || s.end.toLocalDate().equals(date))
                .filter(s -> s.start.isBefore(dayEnd) && s.end.isAfter(dayStart))
                .sorted(Comparator.comparing(s -> s.start))
                .toList();

        List<TimeSlot> free = new ArrayList<>();
        LocalDateTime cursor = dayStart;

        for (TimeSlot busy : dayBusy) {
            LocalDateTime busyStart = busy.start.isBefore(dayStart) ? dayStart : busy.start;
            if (cursor.isBefore(busyStart)) {
                free.add(new TimeSlot(cursor, busyStart));
            }
            LocalDateTime busyEnd = busy.end.isAfter(dayEnd) ? dayEnd : busy.end;
            if (busyEnd.isAfter(cursor)) {
                cursor = busyEnd;
            }
        }

        if (cursor.isBefore(dayEnd)) {
            free.add(new TimeSlot(cursor, dayEnd));
        }

        return free;
    }

    private record TimeSlot(LocalDateTime start, LocalDateTime end) {}
}
