package com.epam.news.admin.controller.error;

import com.epam.news.admin.exception.ControllerException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ErrorController {

    private static final String PAGE_NAME = "error";

    @ExceptionHandler(ControllerException.class)
    public ModelAndView handleCustomException(ControllerException exception) {

        ModelAndView model = new ModelAndView();
        model.setViewName(PAGE_NAME);
        model.addObject("message", exception.getMessage());

        return model;
    }

}
