package com.smartstudy.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "min_session_minutes", nullable = false)
    private Integer minSessionMinutes = 25;

    @Column(name = "max_session_minutes", nullable = false)
    private Integer maxSessionMinutes = 120;

    @Column(name = "break_minutes", nullable = false)
    private Integer breakMinutes = 10;

    @Column(name = "preferred_start_time", nullable = false)
    private LocalTime preferredStartTime = LocalTime.of(8, 0);

    @Column(name = "preferred_end_time", nullable = false)
    private LocalTime preferredEndTime = LocalTime.of(20, 0);

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String name, String description, Integer minSessionMinutes,
                  Integer maxSessionMinutes, Integer breakMinutes, LocalTime preferredStartTime,
                  LocalTime preferredEndTime, List<Chapter> chapters, List<Exam> exams) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minSessionMinutes = minSessionMinutes;
        this.maxSessionMinutes = maxSessionMinutes;
        this.breakMinutes = breakMinutes;
        this.preferredStartTime = preferredStartTime;
        this.preferredEndTime = preferredEndTime;
        this.chapters = chapters;
        this.exams = exams;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinSessionMinutes() {
        return minSessionMinutes;
    }

    public void setMinSessionMinutes(Integer minSessionMinutes) {
        this.minSessionMinutes = minSessionMinutes;
    }

    public Integer getMaxSessionMinutes() {
        return maxSessionMinutes;
    }

    public void setMaxSessionMinutes(Integer maxSessionMinutes) {
        this.maxSessionMinutes = maxSessionMinutes;
    }

    public Integer getBreakMinutes() {
        return breakMinutes;
    }

    public void setBreakMinutes(Integer breakMinutes) {
        this.breakMinutes = breakMinutes;
    }

    public LocalTime getPreferredStartTime() {
        return preferredStartTime;
    }

    public void setPreferredStartTime(LocalTime preferredStartTime) {
        this.preferredStartTime = preferredStartTime;
    }

    public LocalTime getPreferredEndTime() {
        return preferredEndTime;
    }

    public void setPreferredEndTime(LocalTime preferredEndTime) {
        this.preferredEndTime = preferredEndTime;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
