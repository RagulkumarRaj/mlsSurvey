package com.mls.survey.demo.inmemory;

import com.mls.survey.demo.model.QuestionAnswerModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.AbstractMap;

@Profile("prod")
@Configuration
public class AerospikeSupplierImpl implements MapSupplier {

    @Override
    public void addAnswer(final String question, final String answer) {

    }

    @Override
    public QuestionAnswerModel<String, AbstractMap.SimpleEntry<String, Double>> surveyResult() {
          return null;
    }
}
