package com.smartstudy.controller;

import com.smartstudy.dto.ExamRequest;
import com.smartstudy.dto.ExamResponse;
import com.smartstudy.dto.StudySessionResponse;
import com.smartstudy.service.ExamService;
import com.smartstudy.service.StudySessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;
    private final StudySessionService sessionService;

    public ExamController(ExamService examService, StudySessionService sessionService) {
        this.examService = examService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<ExamResponse> findAll() {
        return examService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExamResponse create(@Valid @RequestBody ExamRequest request) {
        return examService.create(request);
    }

    @PutMapping("/{id}")
    public ExamResponse update(@PathVariable Long id, @Valid @RequestBody ExamRequest request) {
        return examService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        examService.delete(id);
    }

    @PostMapping("/{id}/sessions/generate")
    public List<StudySessionResponse> generateSessions(@PathVariable Long id) {
        return sessionService.generateForExam(id);
    }

    @GetMapping("/{id}/sessions")
    public List<StudySessionResponse> getSessions(@PathVariable Long id) {
        return sessionService.findByExam(id);
    }
}
