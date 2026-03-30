package com.smartstudy.controller;

import com.smartstudy.dto.SubchapterRequest;
import com.smartstudy.dto.SubchapterResponse;
import com.smartstudy.service.SubchapterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubchapterController {

    private final SubchapterService subchapterService;

    public SubchapterController(SubchapterService subchapterService) {
        this.subchapterService = subchapterService;
    }

    @GetMapping("/api/chapters/{chapterId}/subchapters")
    public List<SubchapterResponse> findByChapter(@PathVariable Long chapterId) {
        return subchapterService.findByChapter(chapterId);
    }

    @PostMapping("/api/chapters/{chapterId}/subchapters")
    @ResponseStatus(HttpStatus.CREATED)
    public SubchapterResponse create(@PathVariable Long chapterId,
                                     @Valid @RequestBody SubchapterRequest request) {
        return subchapterService.create(chapterId, request);
    }

    @PatchMapping("/api/subchapters/{id}")
    public SubchapterResponse update(@PathVariable Long id,
                                     @Valid @RequestBody SubchapterRequest request) {
        return subchapterService.update(id, request);
    }

    @DeleteMapping("/api/subchapters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        subchapterService.delete(id);
    }
}
