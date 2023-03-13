package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionsAnswerDataStructure;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;
import static java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Profile({"dev", "integration"})
@Configuration
public class UserSurveyDataStoreMapImpl implements UserSurveyDataStore {

    private final Map<String, ConcurrentMap<String, Long>> simpleMapDataStore;
    public UserSurveyDataStoreMapImpl(final Map<String, ConcurrentMap<String, Long>> simpleMapDataStore){
        this.simpleMapDataStore = simpleMapDataStore;
    }

    @Override
    public void captureUserAnswer(final String question, final String answer) {
        final ConcurrentMap<String, Long> answerAndCountMap =
                simpleMapDataStore.computeIfAbsent(question, (k) -> {
                    final ConcurrentHashMap<String, Long> temp = new ConcurrentHashMap<String, Long>();
                    temp.put(answer, 0L);
                    return temp;
                });
        answerAndCountMap.compute(answer, (k,v) -> v != null ? v + 1 : 1);
    }

    @Override
    public QuestionsAnswerDataStructure<String, SimpleEntry<String, Double>> collectSurveyResultFromUserResponsesSoFar() {
        final Map<String, Set<SimpleEntry<String, Double>>> surveyResult = new HashMap<>();
        simpleMapDataStore.entrySet().forEach(entry -> {
            final ConcurrentMap<String, Long> answerAndCountMap =  entry.getValue();
            final Long totalCapturedVotesForQuestion = answerAndCountMap.values().stream().reduce(Long::sum).orElse(0L);
            final Set<SimpleEntry<String, Double>> answerAndPercentMap = new HashSet<>();
            answerAndCountMap.forEach((K, V) -> {
                answerAndPercentMap.add(new SimpleEntry<>(K, (100D*V/totalCapturedVotesForQuestion)));
            });
            surveyResult.put(entry.getKey(), answerAndPercentMap);
        });
        final QuestionsAnswerDataStructure<String, SimpleEntry<String, Double>> answer =
                new QuestionsAnswerDataStructure<>();
        answer.setSubmission(surveyResult);
        return answer;
    }
}
