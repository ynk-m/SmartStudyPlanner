package com.smartstudy.service;

import com.smartstudy.dto.*;
import com.smartstudy.entity.Chapter;
import com.smartstudy.entity.Course;
import com.smartstudy.repository.ChapterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseService courseService;

    public ChapterService(ChapterRepository chapterRepository, CourseService courseService) {
        this.chapterRepository = chapterRepository;
        this.courseService = courseService;
    }

    @Transactional(readOnly = true)
    public List<ChapterResponse> findByCourse(Long courseId) {
        return chapterRepository.findByCourseId(courseId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ChapterResponse create(Long courseId, ChapterRequest request) {
        Course course = courseService.findById(courseId);
        Chapter chapter = new Chapter();
        chapter.setName(request.getName());
        chapter.setCourse(course);
        return toResponse(chapterRepository.save(chapter));
    }

    @Transactional
    public void delete(Long id) {
        chapterRepository.deleteById(id);
    }

    public Chapter findById(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chapter not found: " + id));
    }

    private ChapterResponse toResponse(Chapter chapter) {
        List<SubchapterResponse> subResponses = chapter.getSubchapters().stream()
                .map(sub -> new SubchapterResponse(
                        sub.getId(),
                        sub.getName(),
                        chapter.getId(),
                        sub.getEstimatedMinutes(),
                        sub.getReadinessScore(),
                        sub.getNextReviewAt()
                ))
                .toList();

        return new ChapterResponse(
                chapter.getId(),
                chapter.getName(),
                chapter.getCourse().getId(),
                chapter.getProgress(),
                subResponses
        );
    }
}
