package com.mls.survey.demo.logic;

import com.mls.survey.demo.inmemory.MapSupplier;
import com.mls.survey.demo.model.QuestionAnswerModel;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class SurveyHandler {

    private MapSupplier mapSupplier;
    private QuestionAnswerModel<String, String> questionAnswerModel;

    public SurveyHandler(final MapSupplier mapSupplier){
       this.mapSupplier = mapSupplier;
    }

    public void captureUserSurveySubmission(final String user, final QuestionAnswerModel<String, String> answer){
        CompletableFuture.runAsync(() -> {
            answer.getSubmission().entrySet().forEach((entry) -> {
                add(entry.getKey(), new ArrayList<>(entry.getValue()).get(0));
            });
        });
    }

    public synchronized void  add(final String question, final String answer){
        mapSupplier.addAnswer(question, answer);
    }

    public QuestionAnswerModel<String, AbstractMap.SimpleEntry<String, Double>> surveyResultTillNow(){
       return mapSupplier.surveyResult();
    }

    public void setQuestionAnswerModel(final QuestionAnswerModel questionAnswerModel){
        this.questionAnswerModel = questionAnswerModel;
    }

}
