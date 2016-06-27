package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsViewController {

    private static final String VIEW_ALL_NEWS_PAGE_NAME = "allNewsView";
    private static final String VIEW_SINGLE_NEWS_PAGE_NAME = "newsView";
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news";

    @Autowired
    private NewsManagement management;


    @RequestMapping(method = RequestMethod.GET)
    public String viewAllNews(ModelMap model) throws ControllerException {
        try {
            List<NewsDetailsTO> newsList = management.findAllNewsData();
            model.addAttribute("newsData", newsList);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load all news data", e);
        }

        return VIEW_ALL_NEWS_PAGE_NAME;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewSingleNews(@PathVariable Long id, ModelMap model) throws ControllerException {
        try {
            NewsDetailsTO news = management.getNewsData(id);

            model.addAttribute("newsData", news);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load single news data", e);
        }

        return VIEW_SINGLE_NEWS_PAGE_NAME;
    }

}
