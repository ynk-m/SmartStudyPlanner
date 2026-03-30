package com.smartstudy.dto;

import jakarta.validation.constraints.NotBlank;

public class ChapterRequest {

    @NotBlank
    private String name;

    public ChapterRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
