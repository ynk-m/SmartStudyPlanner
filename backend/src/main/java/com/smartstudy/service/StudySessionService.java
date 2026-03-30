package com.smartstudy.service;

import com.smartstudy.dto.*;
import com.smartstudy.entity.*;
import com.smartstudy.repository.StudySessionRepository;
import com.smartstudy.service.scheduler.SpacedRepetitionEngine;
import com.smartstudy.service.scheduler.StudySchedulerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudySessionService {

    private final StudySessionRepository sessionRepository;
    private final ExamService examService;
    private final StudySchedulerService schedulerService;
    private final SpacedRepetitionEngine spacedRepetitionEngine;

    public StudySessionService(StudySessionRepository sessionRepository,
                               ExamService examService,
                               StudySchedulerService schedulerService,
                               SpacedRepetitionEngine spacedRepetitionEngine) {
        this.sessionRepository = sessionRepository;
        this.examService = examService;
        this.schedulerService = schedulerService;
        this.spacedRepetitionEngine = spacedRepetitionEngine;
    }

    @Transactional(readOnly = true)
    public List<StudySessionResponse> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return sessionRepository.findByStartTimeBetween(start, end).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<StudySessionResponse> findByExam(Long examId) {
        return sessionRepository.findByExamId(examId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public List<StudySessionResponse> generateForExam(Long examId) {
        Exam exam = examService.findById(examId);
        return schedulerService.generateSessions(exam).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public StudySessionResponse update(Long id, SessionUpdateRequest request) {
        StudySession session = findById(id);
        if (request.getStartTime() != null) session.setStartTime(request.getStartTime());
        if (request.getEndTime() != null) session.setEndTime(request.getEndTime());
        return toResponse(sessionRepository.save(session));
    }

    @Transactional
    public StudySessionResponse complete(Long id, SessionCompleteRequest request) {
        StudySession session = findById(id);
        session.setStatus(SessionStatus.COMPLETED);
        session.setFeedback(request.getFeedback());

        // Apply spaced repetition
        Subchapter subchapter = session.getSubchapter();
        spacedRepetitionEngine.applyFeedback(subchapter, request.getFeedback());

        return toResponse(sessionRepository.save(session));
    }

    public StudySession findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudySession not found: " + id));
    }

    private StudySessionResponse toResponse(StudySession session) {
        Subchapter sub = session.getSubchapter();
        Exam exam = session.getExam();

        return new StudySessionResponse(
                session.getId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getStatus(),
                session.getFeedback(),
                sub.getId(),
                sub.getName(),
                exam != null ? exam.getId() : null,
                exam != null ? exam.getTitle() : null,
                sub.getChapter().getCourse().getName()
        );
    }
}
