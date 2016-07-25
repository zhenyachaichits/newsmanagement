package com.epam.news.admin.controller;

import com.epam.news.admin.util.ControllerUtil;
import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.CommentService;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/news"})
public class NewsController {

    private static final String VIEW_ALL_NEWS_PAGE_NAME = "allNewsView";
    private static final String VIEW_SINGLE_NEWS_PAGE_NAME = "newsView";

    private static final String MODEL_TAGS_ATTRIBUTE = "tags";
    private static final String MODEL_AUTHORS_ATTRIBUTE = "authors";
    private static final String MODEL_SEARCH_CRITERIA_ATTRIBUTE = "searchCriteria";
    private static final String MODEL_CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String MODEL_NEWS_DATA_ATTRIBUTE = "newsData";
    private static final String MODEL_PAGES_COUNT_ATTRIBUTE = "pagesCount";
    private static final String MODEL_COMMENT_DATA_ATTRIBUTE = "commentData";

    @Autowired
    private NewsManagement manager;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAllNews(@RequestParam(defaultValue = "1") int page, NewsSearchCriteria criteria,
                              ModelMap model) throws ControllerException {
        try {
            List<NewsDetailsTO> newsList = manager.getNewsForPage(criteria, page);
            int pagesCount = manager.getPagesCount(criteria);
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            model.addAttribute(MODEL_TAGS_ATTRIBUTE, tags);
            model.addAttribute(MODEL_AUTHORS_ATTRIBUTE, authors);
            model.addAttribute(MODEL_SEARCH_CRITERIA_ATTRIBUTE, criteria);
            model.addAttribute(MODEL_CURRENT_PAGE_ATTRIBUTE, page);
            model.addAttribute(MODEL_NEWS_DATA_ATTRIBUTE, newsList);
            model.addAttribute(MODEL_PAGES_COUNT_ATTRIBUTE, pagesCount);

        } catch (ServiceException e) {
            throw new ControllerException("Unable to load all news data", e);
        }

        return VIEW_ALL_NEWS_PAGE_NAME;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewSingleNews(@PathVariable Long id, ModelMap model) throws ControllerException {
        try {
            NewsDetailsTO news = manager.getNewsDetails(id);
            Comment comment = new Comment();

            model.addAttribute(MODEL_NEWS_DATA_ATTRIBUTE, news);
            model.addAttribute(MODEL_COMMENT_DATA_ATTRIBUTE, comment);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load single news data", e);
        }

        return VIEW_SINGLE_NEWS_PAGE_NAME;
    }

}
