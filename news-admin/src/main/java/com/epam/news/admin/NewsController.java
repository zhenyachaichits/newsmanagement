package com.epam.news.admin;

import com.epam.news.common.domain.News;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class NewsController {

    @Autowired
    private NewsService service;

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        try {
            List<News> newsList = service.findAll();
            model.addAttribute("news", newsList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return "hello";
    }

    @RequestMapping("/hey")
    public ModelAndView printHey() {
        String message = "hello bitch";
        ModelAndView mv = new ModelAndView();

        mv.setViewName("hey");
        mv.addObject("imhere", message);

        return mv;
    }
}
