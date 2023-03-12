package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionAnswerModel;
import org.springframework.context.annotation.Configuration;

import java.util.AbstractMap;

@Configuration
public interface MapSupplier {
    void addAnswer(final String question, final String answer);

    QuestionAnswerModel<String, AbstractMap.SimpleEntry<String, Double>> surveyResult();
}
