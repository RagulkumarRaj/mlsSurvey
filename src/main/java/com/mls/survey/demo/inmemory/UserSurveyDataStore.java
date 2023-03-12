package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import org.springframework.context.annotation.Configuration;

import java.util.AbstractMap;

@Configuration
public interface UserSurveyDataStore {
    void captureUserAnswer(final String question, final String answer);
    QuestionsAnswerDataStructure<String, AbstractMap.SimpleEntry<String, Double>> collectSurveyResultFromUserResponsesSoFar();


}
