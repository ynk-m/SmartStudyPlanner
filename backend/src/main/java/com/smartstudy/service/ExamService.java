package com.smartstudy.service;

import com.smartstudy.dto.ExamRequest;
import com.smartstudy.dto.ExamResponse;
import com.smartstudy.entity.Course;
import com.smartstudy.entity.Exam;
import com.smartstudy.entity.SessionStatus;
import com.smartstudy.repository.ExamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseService courseService;

    public ExamService(ExamRepository examRepository, CourseService courseService) {
        this.examRepository = examRepository;
        this.courseService = courseService;
    }

    @Transactional(readOnly = true)
    public List<ExamResponse> findAll() {
        return examRepository.findAllByOrderByExamDateAsc().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ExamResponse create(ExamRequest request) {
        Course course = courseService.findById(request.getCourseId());
        Exam exam = new Exam();
        exam.setTitle(request.getTitle());
        exam.setExamDate(request.getExamDate());
        exam.setNotes(request.getNotes());
        exam.setCourse(course);
        return toResponse(examRepository.save(exam));
    }

    @Transactional
    public ExamResponse update(Long id, ExamRequest request) {
        Exam exam = findById(id);
        exam.setTitle(request.getTitle());
        exam.setExamDate(request.getExamDate());
        exam.setNotes(request.getNotes());
        if (!exam.getCourse().getId().equals(request.getCourseId())) {
            exam.setCourse(courseService.findById(request.getCourseId()));
        }
        return toResponse(examRepository.save(exam));
    }

    @Transactional
    public void delete(Long id) {
        examRepository.deleteById(id);
    }

    public Exam findById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found: " + id));
    }

    private ExamResponse toResponse(Exam exam) {
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), exam.getExamDate().toLocalDate());
        Course course = exam.getCourse();

        double progress = course.getChapters().stream()
                .flatMap(ch -> ch.getSubchapters().stream())
                .mapToDouble(sub -> sub.getReadinessScore())
                .average()
                .orElse(0.0);

        long sessionCount = exam.getStudySessions().stream()
                .filter(s -> s.getStatus() == SessionStatus.COMPLETED)
                .count();

        return new ExamResponse(
                exam.getId(),
                exam.getTitle(),
                exam.getExamDate(),
                exam.getNotes(),
                course.getId(),
                course.getName(),
                Math.max(0, daysRemaining),
                progress,
                (int) sessionCount
        );
    }
}
