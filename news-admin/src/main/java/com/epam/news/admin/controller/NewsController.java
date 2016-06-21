package com.epam.news.admin.controller;

import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private static final String FORWARD_VIEW_PAGE_NAME = "newsView";
    private static final String FORWARD_MANAGEMENT_PAGE_NAME = "newsManagement";
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news";

    @Autowired
    private NewsManagement management;


    @RequestMapping(method = RequestMethod.GET)
    public String viewNews(ModelMap model) {
        try {
            List<NewsDetailsTO> newsList = management.findAllNewsData();
            model.addAttribute("newsData", newsList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return FORWARD_VIEW_PAGE_NAME;
    }

}
