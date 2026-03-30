package com.smartstudy.service.scheduler;

import com.smartstudy.entity.SessionFeedback;
import com.smartstudy.entity.Subchapter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * SM-2-inspired spaced repetition engine.
 * Updates readiness score and calculates next review date based on feedback.
 */
@Component
public class SpacedRepetitionEngine {

    private static final double SCORE_INCREMENT_GOOD = 0.15;
    private static final double SCORE_INCREMENT_MID = 0.0;
    private static final double SCORE_DECREMENT_BAD = -0.2;

    // Base intervals in days, scaled by readiness score
    private static final double[] INTERVAL_TIERS = {1, 2, 4, 7, 14, 30};

    public void applyFeedback(Subchapter subchapter, SessionFeedback feedback) {
        double currentScore = subchapter.getReadinessScore();

        double delta = switch (feedback) {
            case GOOD -> SCORE_INCREMENT_GOOD;
            case MID -> SCORE_INCREMENT_MID;
            case BAD -> SCORE_DECREMENT_BAD;
        };

        double newScore = Math.max(0.0, Math.min(1.0, currentScore + delta));
        subchapter.setReadinessScore(newScore);

        double intervalDays = calculateIntervalDays(newScore);
        subchapter.setNextReviewAt(LocalDateTime.now().plusHours((long) (intervalDays * 24)));
    }

    /**
     * Maps readiness score to a review interval.
     * Low scores -> short intervals (review soon).
     * High scores -> long intervals (mastered, review later).
     */
    private double calculateIntervalDays(double readinessScore) {
        int tierIndex = (int) (readinessScore * (INTERVAL_TIERS.length - 1));
        tierIndex = Math.max(0, Math.min(tierIndex, INTERVAL_TIERS.length - 1));
        return INTERVAL_TIERS[tierIndex];
    }
}
