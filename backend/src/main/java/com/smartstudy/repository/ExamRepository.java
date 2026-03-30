package com.smartstudy.repository;

import com.smartstudy.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCourseId(Long courseId);
    List<Exam> findAllByOrderByExamDateAsc();
}
