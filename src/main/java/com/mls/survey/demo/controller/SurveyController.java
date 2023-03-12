package com.mls.survey.demo.controller;

import com.mls.survey.demo.logic.SurveyHandler;
import com.mls.survey.demo.model.QuestionAnswerModel;
import com.mls.survey.demo.model.UserSurveySubmission;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;

@RestController
public class SurveyController {
    private final SurveyHandler surveyHandler;

    public SurveyController(final SurveyHandler surveyHandler){
        this.surveyHandler = surveyHandler;
    }

    @GetMapping("/survey/result")
     public QuestionAnswerModel<String, AbstractMap.SimpleEntry<String, Double>> surveyResultGet(){
        return surveyHandler.surveyResultTillNow();
    }

    @PostMapping(path = "/survey/questions/upload",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String surveyQuestionsUploadPost(@RequestBody final QuestionAnswerModel<String, String> questionAnswerModel){
        surveyHandler.setQuestionAnswerModel(questionAnswerModel);
        return "Success";
    }

    @PostMapping(path = "/survey/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String surveySubmit(@RequestBody final UserSurveySubmission userSurveySubmission){
         surveyHandler.captureUserSurveySubmission(userSurveySubmission.getUserId(),
                 userSurveySubmission.getQuestionAnswerModel());
        return "Success";
    }
}
