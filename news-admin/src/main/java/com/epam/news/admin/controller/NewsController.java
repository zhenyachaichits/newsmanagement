package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.service.CommentService;
import com.epam.news.common.service.TagService;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = {"/", "/news"})
@ComponentScan("com.epam.news.common")
public class NewsController {

    private static final String VIEW_ALL_NEWS_PAGE_NAME = "allNewsView";
    private static final String VIEW_SINGLE_NEWS_PAGE_NAME = "newsView";
    private static final String REDIRECT_NEWS_VALUE = "redirect:/news";

    @Autowired
    private TagService tagService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private NewsManagement management;
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAllNews(@RequestParam(defaultValue = "1") int page, NewsSearchCriteria criteria,
                              ModelMap model) throws ControllerException {
        try {
            List<NewsDetailsTO> newsList = management.getNewsForPage(page);
            int pagesCount = management.getPagesCount();

            List<Tag> tags = tagService.findAll();
            List<Author> authors = authorService.findAll();

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
            NewsDetailsTO news = management.getNewsData(id);
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

        // TODO: 7/11/2016 REFACTOR
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
        //return REDIRECT_NEWS_VALUE;
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public String deleteNewsComment(@RequestParam Long id) throws ControllerException {
        try {
            commentService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete news comment data", e);
        }

        return REDIRECT_NEWS_VALUE;
    }

}
