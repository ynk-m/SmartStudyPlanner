package com.smartstudy.repository;

import com.smartstudy.entity.SessionStatus;
import com.smartstudy.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    List<StudySession> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<StudySession> findByExamId(Long examId);

    List<StudySession> findBySubchapterId(Long subchapterId);

    List<StudySession> findByStatusAndStartTimeBetween(
            SessionStatus status, LocalDateTime start, LocalDateTime end);

    List<StudySession> findByExamIdAndStatusAndStartTimeAfter(
            Long examId, SessionStatus status, LocalDateTime after);
}
