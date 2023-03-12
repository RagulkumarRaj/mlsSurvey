package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionAnswerModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;
import static java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Profile("dev")
@Configuration
public class MapSupplierImpl implements MapSupplier {
    private Map<String, ConcurrentMap<String, Long>> result = new ConcurrentHashMap<>();
    @Override
    public void addAnswer(final String question, final String answer) {
        final ConcurrentMap<String, Long> map =
                result.computeIfAbsent(question, (k) -> {
                    final ConcurrentHashMap<String, Long> answerCountMap = new ConcurrentHashMap<String, Long>();
                    answerCountMap.put(answer, 0L);
                    return answerCountMap;
                });
        map.compute(answer, (k,v) -> v != null ? v + 1 : 1);
    }

    @Override
    public QuestionAnswerModel<String, SimpleEntry<String, Double>> surveyResult() {
        final Map<String, Set<SimpleEntry<String, Double>>> resultCopy = new HashMap<>();
        result.entrySet().forEach(e -> {
            final ConcurrentMap<String, Long> value =  e.getValue();
            final Long totalVotes = value.values().stream().reduce(Long::sum).orElse(0L);
            final Set<SimpleEntry<String, Double>> set = new HashSet<>();
            value.forEach((K, V) -> {
                set.add(new SimpleEntry<>(K, (100D*V/totalVotes)));
            });
            resultCopy.put(e.getKey(), set);
        });
        final QuestionAnswerModel<String, SimpleEntry<String, Double>> answer =
                new QuestionAnswerModel<>();
        answer.setSubmission(resultCopy);
        return answer;
    }
}
