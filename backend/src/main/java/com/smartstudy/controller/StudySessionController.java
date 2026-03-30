package com.smartstudy.controller;

import com.smartstudy.dto.*;
import com.smartstudy.service.StudySessionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class StudySessionController {

    private final StudySessionService sessionService;

    public StudySessionController(StudySessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<StudySessionResponse> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return sessionService.findByDateRange(start, end);
    }

    @PatchMapping("/{id}")
    public StudySessionResponse update(@PathVariable Long id,
                                       @Valid @RequestBody SessionUpdateRequest request) {
        return sessionService.update(id, request);
    }

    @PostMapping("/{id}/complete")
    public StudySessionResponse complete(@PathVariable Long id,
                                         @Valid @RequestBody SessionCompleteRequest request) {
        return sessionService.complete(id, request);
    }
}
