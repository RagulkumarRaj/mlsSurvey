package com.mls.survey.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mls.survey.demo.logic", "com.mls.survey.demo.inmemory", "com.mls.survey.demo.controller"})
public class UserSurveyApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserSurveyApplication.class, args);
	}
}
