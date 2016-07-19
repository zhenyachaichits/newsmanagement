package com.epam.news.admin.controller;


import com.epam.news.admin.exception.ControllerException;
import com.epam.news.admin.util.ControllerUtil;
import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/newsManagement")
public class NewsManagementController {

    private static final String NEWS_MANAGEMENT_PAGE_NAME = "newsManagement";
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news";

    @Autowired
    private NewsManagement manager;

    @RequestMapping(method = RequestMethod.GET)
    public String viewNewsManagement(ModelMap model) throws ControllerException {
        try {
            NewsTO news = new NewsTO();
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            model.addAttribute("newsData", news);
            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load management data", e);
        }

        return NEWS_MANAGEMENT_PAGE_NAME;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addNews(@Valid NewsTO newsData, BindingResult bindingResult,
                          HttpServletRequest request) throws ControllerException {
        try {
            if (bindingResult.hasErrors()) {
                return ControllerUtil.redirectToPrevious(request);
            }
            manager.saveNewsData(newsData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to save news data");
        }

        return REDIRECT_NEWS_VALUE;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewNewsManagement(@PathVariable Long id, ModelMap model) throws ControllerException {
        try {

            NewsTO newsData = manager.getNewsData(id);
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            model.addAttribute("newsData", newsData);
            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to show news management data");
        }

        return NEWS_MANAGEMENT_PAGE_NAME;
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String deleteNews(@RequestParam Long[] newsIds) throws ControllerException {
        try {
            manager.deleteNewsData(newsIds);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete news data");
        }

        return REDIRECT_NEWS_VALUE;
    }

}
