package com.mls.survey.demo.controller;

import com.mls.survey.demo.logic.UsersSurveyHandler;
import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import com.mls.survey.demo.model.UserSurveySubmission;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;

@RestController
public class UserSurveyController {
    private final UsersSurveyHandler usersSurveyHandler;

    public UserSurveyController(final UsersSurveyHandler usersSurveyHandler){
        this.usersSurveyHandler = usersSurveyHandler;
    }

    @GetMapping(path="/survey/result",
            produces = MediaType.APPLICATION_JSON_VALUE)
     public QuestionsAnswerDataStructure<String, AbstractMap.SimpleEntry<String, Double>> surveyResultGet(){
        return usersSurveyHandler.surveyResultOfAllUsersTillNow();
    }

    @PostMapping(path = "/survey/questions/upload",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String surveyQuestionsUploadPost(@RequestBody final QuestionsAnswerDataStructure<String, String> questionsAnswerDataStructure){
        usersSurveyHandler.addAllQuestionsAndPossibleAnswers(questionsAnswerDataStructure);
        return "Success";
    }

    @PostMapping(path = "/survey/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String surveySubmit(@RequestBody final UserSurveySubmission userSurveySubmission){
         usersSurveyHandler.captureQASubmissionOfUser(userSurveySubmission.getUserId(),
                 userSurveySubmission.getQuestionAnswerModel());
        return "Success";
    }
}
