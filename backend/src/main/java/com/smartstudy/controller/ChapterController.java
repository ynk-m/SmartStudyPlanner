package com.smartstudy.controller;

import com.smartstudy.dto.ChapterRequest;
import com.smartstudy.dto.ChapterResponse;
import com.smartstudy.service.ChapterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/api/courses/{courseId}/chapters")
    public List<ChapterResponse> findByCourse(@PathVariable Long courseId) {
        return chapterService.findByCourse(courseId);
    }

    @PostMapping("/api/courses/{courseId}/chapters")
    @ResponseStatus(HttpStatus.CREATED)
    public ChapterResponse create(@PathVariable Long courseId,
                                  @Valid @RequestBody ChapterRequest request) {
        return chapterService.create(courseId, request);
    }

    @DeleteMapping("/api/chapters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        chapterService.delete(id);
    }
}
