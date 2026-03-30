package com.smartstudy.service;

import com.smartstudy.dto.CourseRequest;
import com.smartstudy.dto.CourseResponse;
import com.smartstudy.entity.Course;
import com.smartstudy.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public CourseResponse create(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setMinSessionMinutes(request.getMinSessionMinutes());
        course.setMaxSessionMinutes(request.getMaxSessionMinutes());
        course.setBreakMinutes(request.getBreakMinutes());
        course.setPreferredStartTime(request.getPreferredStartTime());
        course.setPreferredEndTime(request.getPreferredEndTime());
        return toResponse(courseRepository.save(course));
    }

    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = findById(id);
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setMinSessionMinutes(request.getMinSessionMinutes());
        course.setMaxSessionMinutes(request.getMaxSessionMinutes());
        course.setBreakMinutes(request.getBreakMinutes());
        course.setPreferredStartTime(request.getPreferredStartTime());
        course.setPreferredEndTime(request.getPreferredEndTime());
        return toResponse(courseRepository.save(course));
    }

    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + id));
    }

    private CourseResponse toResponse(Course course) {
        double progress = course.getChapters().stream()
                .flatMap(ch -> ch.getSubchapters().stream())
                .mapToDouble(sub -> sub.getReadinessScore())
                .average()
                .orElse(0.0);

        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getMinSessionMinutes(),
                course.getMaxSessionMinutes(),
                course.getBreakMinutes(),
                course.getPreferredStartTime(),
                course.getPreferredEndTime(),
                progress,
                course.getChapters().size()
        );
    }
}
