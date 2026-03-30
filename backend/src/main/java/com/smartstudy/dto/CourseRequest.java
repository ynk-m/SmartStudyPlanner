package com.smartstudy.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public class CourseRequest {

    @NotBlank
    private String name;

    private String description;

    @Min(5)
    private Integer minSessionMinutes = 25;

    @Min(10)
    private Integer maxSessionMinutes = 120;

    @Min(0)
    private Integer breakMinutes = 10;

    private LocalTime preferredStartTime = LocalTime.of(8, 0);

    private LocalTime preferredEndTime = LocalTime.of(20, 0);

    public CourseRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinSessionMinutes() {
        return minSessionMinutes;
    }

    public void setMinSessionMinutes(Integer minSessionMinutes) {
        this.minSessionMinutes = minSessionMinutes;
    }

    public Integer getMaxSessionMinutes() {
        return maxSessionMinutes;
    }

    public void setMaxSessionMinutes(Integer maxSessionMinutes) {
        this.maxSessionMinutes = maxSessionMinutes;
    }

    public Integer getBreakMinutes() {
        return breakMinutes;
    }

    public void setBreakMinutes(Integer breakMinutes) {
        this.breakMinutes = breakMinutes;
    }

    public LocalTime getPreferredStartTime() {
        return preferredStartTime;
    }

    public void setPreferredStartTime(LocalTime preferredStartTime) {
        this.preferredStartTime = preferredStartTime;
    }

    public LocalTime getPreferredEndTime() {
        return preferredEndTime;
    }

    public void setPreferredEndTime(LocalTime preferredEndTime) {
        this.preferredEndTime = preferredEndTime;
    }
}
