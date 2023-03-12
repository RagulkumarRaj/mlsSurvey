package com.mls.survey.demo.model;

public class UserSurveySubmission {
    private String userId;
    private QuestionsAnswerDataStructure<String, String> questionsAnswerDataStructure;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public QuestionsAnswerDataStructure getQuestionAnswerModel() {
        return questionsAnswerDataStructure;
    }

    public void setQuestionAnswerModel(final QuestionsAnswerDataStructure questionsAnswerDataStructure) {
        this.questionsAnswerDataStructure = questionsAnswerDataStructure;
    }
}
