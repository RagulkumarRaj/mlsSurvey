package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.AbstractMap;

@Profile("prod")
@Configuration
public class UserSurveyDataStoreAerospikeImpl implements UserSurveyDataStore {

    @Override
    public void captureUserAnswer(final String question, final String answer) {

    }

    @Override
    public QuestionsAnswerDataStructure<String, AbstractMap.SimpleEntry<String, Double>> collectSurveyResultFromUserResponsesSoFar() {
          return null;
    }
}
