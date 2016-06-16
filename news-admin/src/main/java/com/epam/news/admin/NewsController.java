package com.epam.news.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class NewsController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printHello() {
        String message = "hello bitch";

        return new ModelAndView("hello", "message", message);
    }
}
