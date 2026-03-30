package com.smartstudy.dto;

import com.smartstudy.entity.SessionFeedback;
import jakarta.validation.constraints.NotNull;

public class SessionCompleteRequest {

    @NotNull
    private SessionFeedback feedback;

    public SessionCompleteRequest() {
    }

    public SessionFeedback getFeedback() {
        return feedback;
    }

    public void setFeedback(SessionFeedback feedback) {
        this.feedback = feedback;
    }
}
