package com.mls.survey.demo.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DemoErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(final HttpServletResponse response) {
        final int status = response.getStatus();
        if ( status == HttpStatus.NOT_FOUND.value()) {
            return new ModelAndView("error-404.html");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new ModelAndView("error-500.html");
        }
        return new ModelAndView("error.html");
    }
}
