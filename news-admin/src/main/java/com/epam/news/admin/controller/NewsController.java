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
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news";

    @Autowired
    private NewsManagement manager;
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAllNews(@RequestParam(defaultValue = "1") int page, NewsSearchCriteria criteria,
                              ModelMap model) throws ControllerException {
        try {
            List<NewsDetailsTO> newsList = manager.getNewsForPage(criteria, page);
            int pagesCount = manager.getPagesCount();
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
            model.addAttribute("searchCriteria", criteria);
            model.addAttribute("currentPage", page);
            model.addAttribute("newsData", newsList);
            model.addAttribute("pagesCount", pagesCount);

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

            model.addAttribute("newsData", news);
            model.addAttribute("commentData", comment);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to load single news data", e);
        }

        return VIEW_SINGLE_NEWS_PAGE_NAME;
    }


    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addNewsComment(Comment commentData, HttpServletRequest request) throws ControllerException {
        try {
            commentService.save(commentData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to add news comment data", e);
        }

        return ControllerUtil.redirectToPrevious(request);
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public String deleteNewsComment(@RequestParam Long id, HttpServletRequest request) throws ControllerException {
        try {
            commentService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete news comment data", e);
        }

        return ControllerUtil.redirectToPrevious(request);
    }

}
