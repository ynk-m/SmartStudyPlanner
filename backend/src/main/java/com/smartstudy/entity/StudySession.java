package com.smartstudy.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "study_session")
public class StudySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status = SessionStatus.SCHEDULED;

    @Enumerated(EnumType.STRING)
    private SessionFeedback feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subchapter_id", nullable = false)
    private Subchapter subchapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public StudySession() {
    }

    public StudySession(Long id, LocalDateTime startTime, LocalDateTime endTime,
                        SessionStatus status, SessionFeedback feedback,
                        Subchapter subchapter, Exam exam) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.feedback = feedback;
        this.subchapter = subchapter;
        this.exam = exam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public SessionFeedback getFeedback() {
        return feedback;
    }

    public void setFeedback(SessionFeedback feedback) {
        this.feedback = feedback;
    }

    public Subchapter getSubchapter() {
        return subchapter;
    }

    public void setSubchapter(Subchapter subchapter) {
        this.subchapter = subchapter;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
