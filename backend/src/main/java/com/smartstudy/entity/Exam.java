package com.smartstudy.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "exam_date", nullable = false)
    private LocalDateTime examDate;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudySession> studySessions = new ArrayList<>();

    public Exam() {
    }

    public Exam(Long id, String title, LocalDateTime examDate, String notes,
                Course course, List<StudySession> studySessions) {
        this.id = id;
        this.title = title;
        this.examDate = examDate;
        this.notes = notes;
        this.course = course;
        this.studySessions = studySessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<StudySession> getStudySessions() {
        return studySessions;
    }

    public void setStudySessions(List<StudySession> studySessions) {
        this.studySessions = studySessions;
    }
}
