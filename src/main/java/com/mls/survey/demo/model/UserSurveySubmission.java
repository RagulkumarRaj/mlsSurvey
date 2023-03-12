package com.mls.survey.demo.model;

public class UserSurveySubmission {
    private String userId;
    private QuestionAnswerModel<String, String> questionAnswerModel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public QuestionAnswerModel getQuestionAnswerModel() {
        return questionAnswerModel;
    }

    public void setQuestionAnswerModel(final QuestionAnswerModel questionAnswerModel) {
        this.questionAnswerModel = questionAnswerModel;
    }
}
