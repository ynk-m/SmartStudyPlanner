package com.smartstudy.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subchapter> subchapters = new ArrayList<>();

    public Chapter() {
    }

    public Chapter(Long id, String name, Course course, List<Subchapter> subchapters) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.subchapters = subchapters;
    }

    public double getProgress() {
        if (subchapters.isEmpty()) return 0.0;
        return subchapters.stream()
                .mapToDouble(Subchapter::getReadinessScore)
                .average()
                .orElse(0.0);
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Subchapter> getSubchapters() {
        return subchapters;
    }

    public void setSubchapters(List<Subchapter> subchapters) {
        this.subchapters = subchapters;
    }
}
