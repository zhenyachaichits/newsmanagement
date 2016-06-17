package com.epam.news.admin;

import com.epam.news.common.domain.News;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.NewsService;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class NewsController {

    @Autowired
    private NewsService service;


    @RequestMapping(method = RequestMethod.GET)
    public String addNews(ModelMap model) {
        try {
            List<News> newsList = service.findAll();
            model.addAttribute("news", newsList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return "hello";
    }



}
