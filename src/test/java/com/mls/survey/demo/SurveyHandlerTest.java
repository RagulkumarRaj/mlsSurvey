package com.mls.survey.demo;

import com.mls.survey.demo.logic.SurveyHandler;
import com.mls.survey.demo.inmemory.MapSupplierImpl;
import com.mls.survey.demo.model.QuestionAnswerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SurveyHandlerTest {

    private SurveyHandler surveyHandler;
    @BeforeEach
    public void setUp(){
        surveyHandler = new SurveyHandler(new MapSupplierImpl());
    }

    @Test
    public void testForHugeUserLoad(){
        int[][] qA = new int[5][4];
        final Random random = new Random();
        for(int i=0; i<1000; i++){
            for(int j=0; j<qA.length; j++){
                final int randInt = random.nextInt(4);
                int ans = qA[j][randInt];
                qA[j][randInt] = ++ans;
                surveyHandler.add("q"+j, "a"+randInt);
            }
        }
        final QuestionAnswerModel<String, AbstractMap.SimpleEntry<String, Double>> surveyResult =
                surveyHandler.surveyResultTillNow();
        final Map<String, Set<AbstractMap.SimpleEntry<String, Double>>> surveyResultMap =  surveyResult.getSubmission();
        for(int i=0; i<5; i++){
            final Set<AbstractMap.SimpleEntry<String, Double>> set = surveyResultMap.get("q"+i);
            for(AbstractMap.SimpleEntry<String, Double> entry : set){
                for(int j=0; j<4; j++){
                    if(("a"+j).equals(entry.getKey())){
                        Assert.state(entry.getValue() == (qA[i][j] * 100D)/1000, "Answer distribution percentage matches");
                    }
                }
            }
        }
    }
}
