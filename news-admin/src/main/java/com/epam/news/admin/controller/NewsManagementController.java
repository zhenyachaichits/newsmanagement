package com.epam.news.admin.controller;


import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.service.TagService;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/newsManagement")
public class NewsManagementController {

    private static final String FORWARD_PAGE_NAME = "newsManagement";
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news";

    @Autowired
    private TagService tagService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private NewsManagement manager;

    @RequestMapping(method = RequestMethod.GET)
    public String viewNewsManagement(ModelMap model) throws ControllerException {
        try {
            NewsTO news = new NewsTO();
            List<Tag> tags = tagService.findAll();
            List<Author> authors = authorService.findAll();

            model.addAttribute("newsData", news);
            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load management data", e);
        }

        return FORWARD_PAGE_NAME;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNews(NewsTO newsData) throws ControllerException {
        try {
            manager.addNewsData(newsData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to save news data");
        }

        return REDIRECT_NEWS_VALUE;
    }

     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public String viewNewsManagement(@PathVariable Long id, ModelMap model) throws ControllerException {
         try {
             NewsDetailsTO news = manager.getNewsData(id);
             List<Tag> tags = tagService.findAll();
             List<Author> authors = authorService.findAll();

             model.addAttribute("newsData", news);
             model.addAttribute("tags", tags);
             model.addAttribute("authors", authors);
         } catch (ServiceException e) {
             throw new ControllerException("Unable to show news management data");
         }

         return FORWARD_PAGE_NAME;
     }

}
