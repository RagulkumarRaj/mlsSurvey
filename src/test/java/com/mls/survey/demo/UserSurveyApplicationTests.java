package com.mls.survey.demo;

import com.mls.survey.demo.controller.UserSurveyController;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserSurveyController.class)
class UserSurveyApplicationTests {
	@Test
	void contextLoads() {
	}
}
