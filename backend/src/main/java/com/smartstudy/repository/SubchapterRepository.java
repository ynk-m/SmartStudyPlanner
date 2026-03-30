package com.smartstudy.repository;

import com.smartstudy.entity.Subchapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SubchapterRepository extends JpaRepository<Subchapter, Long> {

    List<Subchapter> findByChapterId(Long chapterId);

    @Query("SELECT s FROM Subchapter s WHERE s.chapter.course.id = :courseId " +
           "AND (s.readinessScore < :threshold OR s.nextReviewAt <= :now) " +
           "ORDER BY s.nextReviewAt ASC NULLS FIRST, s.readinessScore ASC")
    List<Subchapter> findSubchaptersNeedingReview(
            @Param("courseId") Long courseId,
            @Param("threshold") Double threshold,
            @Param("now") LocalDateTime now);
}
