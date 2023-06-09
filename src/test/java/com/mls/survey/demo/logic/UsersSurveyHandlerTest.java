package com.mls.survey.demo.logic;

import com.mls.survey.demo.inmemory.UserSurveyDataStoreMapImpl;
import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.*;

public class UsersSurveyHandlerTest {

    private UsersSurveyHandler usersSurveyHandler;
    @BeforeEach
    public void setUp(){
        usersSurveyHandler = new UsersSurveyHandler(new UserSurveyDataStoreMapImpl(new HashMap<>()));
    }

    @Test
    public void testAddAnswerAndSurveyResultFor1000Users(){
        //userQuestionAnswerResponseArray contains the 1000 users answers for all questions. This array acts as a
        // source of truth for the test cases
        final int[][] userQuestionAnswerResponseArray = new int[5][4];
        final Random random = new Random();
        for(int i=0; i<1000; i++){
            for(int j=0; j<userQuestionAnswerResponseArray.length; j++){
                final int randInt = random.nextInt(4);
                int ans = userQuestionAnswerResponseArray[j][randInt];
                userQuestionAnswerResponseArray[j][randInt] = ++ans;
                usersSurveyHandler.addUserQAResponse("q"+j, "a"+randInt);
            }
        }
        final QuestionsAnswerDataStructure<String, AbstractMap.SimpleEntry<String, Double>> surveyResult =
                usersSurveyHandler.surveyResultOfAllUsersTillNow();
        final Map<String, Set<AbstractMap.SimpleEntry<String, Double>>> surveyResultMap =  surveyResult.getSubmission();
        for(int i=0; i<5; i++){
            final Set<AbstractMap.SimpleEntry<String, Double>> set = surveyResultMap.get("q"+i);
            for(AbstractMap.SimpleEntry<String, Double> entry : set){
                for(int j=0; j<4; j++){
                    if(("a"+j).equals(entry.getKey())){
                        Assert.state(entry.getValue() == (userQuestionAnswerResponseArray[i][j] * 100D)/1000, "Answer distribution percentage matches");
                    }
                }
            }
        }
    }
}
