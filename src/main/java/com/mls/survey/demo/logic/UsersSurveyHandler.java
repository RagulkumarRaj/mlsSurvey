package com.mls.survey.demo.logic;

import com.mls.survey.demo.inmemory.UserSurveyDataStore;
import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class UsersSurveyHandler {

    private final UserSurveyDataStore userSurveyDataStore;
    private QuestionsAnswerDataStructure<String, String> questionsAnswerDataStructure;

    public UsersSurveyHandler(final UserSurveyDataStore userSurveyDataStore){
       this.userSurveyDataStore = userSurveyDataStore;
    }

    public void captureQASubmissionOfUser(final String user, final QuestionsAnswerDataStructure<String, String> answer){
        CompletableFuture.runAsync(() -> {
            answer.getSubmission().entrySet().forEach((entry) -> {
                addUserQAResponse(entry.getKey(), new ArrayList<>(entry.getValue()).get(0));
            });
        });
    }

    public synchronized void addUserQAResponse(final String question, final String answer){
        userSurveyDataStore.captureUserAnswer(question, answer);
    }

    public QuestionsAnswerDataStructure<String, AbstractMap.SimpleEntry<String, Double>> surveyResultOfAllUsersTillNow(){
       return userSurveyDataStore.collectSurveyResultFromUserResponsesSoFar();
    }

    public void addAllQuestionsAndPossibleAnswers(final QuestionsAnswerDataStructure questionsAnswerDataStructure){
        this.questionsAnswerDataStructure = questionsAnswerDataStructure;
    }
}
