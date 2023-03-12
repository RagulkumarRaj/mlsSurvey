package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Set;

class UserSurveyDataStoreTest {

    private UserSurveyDataStoreMapImpl mapSupplier;
    @BeforeEach
    public void setUp(){
        mapSupplier = new UserSurveyDataStoreMapImpl(new HashMap<>());
    }

    @Test
    public void testAddAnswersAndSurveyResult(){
        mapSupplier.captureUserAnswer("question 1",  "answer 1");
        mapSupplier.captureUserAnswer("question 2", "answer 2");
        mapSupplier.captureUserAnswer("question 1", "answer 1");
        mapSupplier.captureUserAnswer("question 2", "answer 3");
        mapSupplier.captureUserAnswer("question 1", "answer 1");
        mapSupplier.captureUserAnswer("question 2", "answer 2");
        final QuestionsAnswerDataStructure<String, AbstractMap.SimpleEntry<String, Double>> surveyResultModel =  mapSupplier.collectSurveyResultFromUserResponsesSoFar();
        Set<AbstractMap.SimpleEntry<String, Double> > answerDistribution = surveyResultModel.getSubmission().get("question 1");
        answerDistribution.forEach(entry -> {
            if(entry.getKey().equals("answer 1")){
                 Assert.state(entry.getValue() == 100, "All users selected answer 1 for question 1");
            }
        });
        answerDistribution = surveyResultModel.getSubmission().get("question 2");
        answerDistribution.forEach(entry -> {
            if(entry.getKey().equals("answer 2")){
                Assert.state("66,67".equals(new DecimalFormat("0.00").format(entry.getValue())),
                        "2/3 users selected answer 2 for question 2");
            }
            if(entry.getKey().equals("answer 3")){
                Assert.state("33,33".equals(new DecimalFormat("0.00").format(entry.getValue())),
                        "1/3 users selected answer 3 for question 2");
            }
        });
    }

}
