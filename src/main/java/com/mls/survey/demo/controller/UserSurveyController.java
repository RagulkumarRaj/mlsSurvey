package com.mls.survey.demo.controller;

import com.mls.survey.demo.logic.UsersSurveyHandler;
import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import com.mls.survey.demo.model.UserSurveySubmission;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class UserSurveyController {
    private final UsersSurveyHandler usersSurveyHandler;

    public UserSurveyController(final UsersSurveyHandler usersSurveyHandler){
        this.usersSurveyHandler = usersSurveyHandler;
    }

    @GetMapping(path="/result",
            produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity surveyResultGet(){
        return ResponseEntity.ok(usersSurveyHandler.surveyResultOfAllUsersTillNow());
    }

    @PostMapping(path = "/questions/upload",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity surveyQuestionsUploadPost(
            @RequestBody final QuestionsAnswerDataStructure<String, String> questionsAnswerDataStructure){
        usersSurveyHandler.addAllQuestionsAndPossibleAnswers(questionsAnswerDataStructure);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(path = "/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity surveySubmit(@RequestBody final UserSurveySubmission userSurveySubmission){
         usersSurveyHandler.captureQASubmissionOfUser(userSurveySubmission.getUserId(),
                 userSurveySubmission.getQuestionAnswerModel());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
