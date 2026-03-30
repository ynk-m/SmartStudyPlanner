package com.smartstudy.service;

import com.smartstudy.dto.SubchapterRequest;
import com.smartstudy.dto.SubchapterResponse;
import com.smartstudy.entity.Chapter;
import com.smartstudy.entity.Subchapter;
import com.smartstudy.repository.SubchapterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubchapterService {

    private final SubchapterRepository subchapterRepository;
    private final ChapterService chapterService;

    public SubchapterService(SubchapterRepository subchapterRepository, ChapterService chapterService) {
        this.subchapterRepository = subchapterRepository;
        this.chapterService = chapterService;
    }

    @Transactional(readOnly = true)
    public List<SubchapterResponse> findByChapter(Long chapterId) {
        return subchapterRepository.findByChapterId(chapterId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public SubchapterResponse create(Long chapterId, SubchapterRequest request) {
        Chapter chapter = chapterService.findById(chapterId);
        Subchapter subchapter = new Subchapter();
        subchapter.setName(request.getName());
        subchapter.setEstimatedMinutes(request.getEstimatedMinutes());
        subchapter.setChapter(chapter);
        return toResponse(subchapterRepository.save(subchapter));
    }

    @Transactional
    public SubchapterResponse update(Long id, SubchapterRequest request) {
        Subchapter subchapter = findById(id);
        subchapter.setName(request.getName());
        subchapter.setEstimatedMinutes(request.getEstimatedMinutes());
        return toResponse(subchapterRepository.save(subchapter));
    }

    @Transactional
    public void delete(Long id) {
        subchapterRepository.deleteById(id);
    }

    public Subchapter findById(Long id) {
        return subchapterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subchapter not found: " + id));
    }

    private SubchapterResponse toResponse(Subchapter sub) {
        return new SubchapterResponse(
                sub.getId(),
                sub.getName(),
                sub.getChapter().getId(),
                sub.getEstimatedMinutes(),
                sub.getReadinessScore(),
                sub.getNextReviewAt()
        );
    }
}
