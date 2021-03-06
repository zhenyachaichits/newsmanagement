package com.epam.news.admin.controller;


import com.epam.news.admin.exception.ControllerException;
import com.epam.news.admin.util.ControllerUtil;
import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/newsManagement")
public class NewsManagementController {

    private static final String NEWS_MANAGEMENT_PAGE_NAME = "newsManagement";
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news/";

    private static final String MODEL_TAGS_ATTRIBUTE = "tags";
    private static final String MODEL_AUTHORS_ATTRIBUTE = "authors";
    private static final String MODEL_NEWS_DATA_ATTRIBUTE = "newsData";

    @Autowired
    private NewsManagement manager;

    @Autowired
    @Qualifier("newsFormValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewNewsManagement(ModelMap model) throws ControllerException {
        try {
            NewsTO news = new NewsTO();
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            model.addAttribute(MODEL_NEWS_DATA_ATTRIBUTE, news);
            model.addAttribute(MODEL_TAGS_ATTRIBUTE, tags);
            model.addAttribute(MODEL_AUTHORS_ATTRIBUTE, authors);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load management data", e);
        }

        return NEWS_MANAGEMENT_PAGE_NAME;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewNewsManagement(@PathVariable Long id, ModelMap model) throws ControllerException {
        try {

            NewsTO newsData = manager.getNewsData(id);
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            model.addAttribute(MODEL_NEWS_DATA_ATTRIBUTE, newsData);
            model.addAttribute(MODEL_TAGS_ATTRIBUTE, tags);
            model.addAttribute(MODEL_AUTHORS_ATTRIBUTE, authors);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to show news management data", e);
        }

        return NEWS_MANAGEMENT_PAGE_NAME;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNews(@ModelAttribute(MODEL_NEWS_DATA_ATTRIBUTE) @Validated NewsTO newsData,
                           BindingResult bindingResult, ModelMap model) throws ControllerException {
        try {
            if (bindingResult.hasErrors()) {
                List<Tag> tags = manager.getAllTags();
                List<Author> authors = manager.getAllAuthors();

                model.addAttribute(MODEL_TAGS_ATTRIBUTE, tags);
                model.addAttribute(MODEL_AUTHORS_ATTRIBUTE, authors);
                return NEWS_MANAGEMENT_PAGE_NAME;
            }
            Long newsId =  manager.saveNewsData(newsData);

            return REDIRECT_NEWS_VALUE + newsId;
        } catch (ServiceException e) {
            throw new ControllerException("Unable to save news data", e);
        }
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String deleteNews(@RequestParam Long[] newsIds) throws ControllerException {
        try {
            manager.deleteNewsData(newsIds);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete news data", e);
        }

        return REDIRECT_NEWS_VALUE;
    }

}
