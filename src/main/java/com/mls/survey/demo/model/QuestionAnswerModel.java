package com.mls.survey.demo.model;

import java.util.Map;
import java.util.Set;

public class QuestionAnswerModel<T, U> {
    private Map<T, Set<U>> submission;

    public Map<T, Set<U>> getSubmission() {
        return submission;
    }

    public void setSubmission(final Map<T, Set<U>> submission) {
        this.submission = submission;
    }
}
