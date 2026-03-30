package com.smartstudy.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subchapter")
public class Subchapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "estimated_minutes", nullable = false)
    private Integer estimatedMinutes;

    @Column(name = "readiness_score", nullable = false)
    private Double readinessScore = 0.0;

    @Column(name = "next_review_at")
    private LocalDateTime nextReviewAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @OneToMany(mappedBy = "subchapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudySession> studySessions = new ArrayList<>();

    public Subchapter() {
    }

    public Subchapter(Long id, String name, Integer estimatedMinutes, Double readinessScore,
                      LocalDateTime nextReviewAt, Chapter chapter, List<StudySession> studySessions) {
        this.id = id;
        this.name = name;
        this.estimatedMinutes = estimatedMinutes;
        this.readinessScore = readinessScore;
        this.nextReviewAt = nextReviewAt;
        this.chapter = chapter;
        this.studySessions = studySessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public Double getReadinessScore() {
        return readinessScore;
    }

    public void setReadinessScore(Double readinessScore) {
        this.readinessScore = readinessScore;
    }

    public LocalDateTime getNextReviewAt() {
        return nextReviewAt;
    }

    public void setNextReviewAt(LocalDateTime nextReviewAt) {
        this.nextReviewAt = nextReviewAt;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<StudySession> getStudySessions() {
        return studySessions;
    }

    public void setStudySessions(List<StudySession> studySessions) {
        this.studySessions = studySessions;
    }
}
