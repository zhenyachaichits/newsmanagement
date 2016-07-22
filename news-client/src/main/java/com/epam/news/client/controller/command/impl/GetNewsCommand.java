package com.epam.news.client.controller.command.impl;

import com.epam.news.client.controller.command.Command;
import com.epam.news.client.exception.BeanCompilerException;
import com.epam.news.client.exception.CommandException;
import com.epam.news.client.util.compiler.BeanCompiler;
import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class GetNewsCommand implements Command {

    private static final String REQUEST_PAGE_PARAMETER = "page";
    private static final String REQUEST_TAGS_ATTRIBUTE = "tags";
    private static final String REQUEST_AUTHORS_ATTRIBUTE = "authors";
    private static final String REQUEST_SEARCH_CRITERIA_ATTRIBUTE = "searchCriteria";
    private static final String REQUEST_CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String REQUEST_NEWS_DATA_ATTRIBUTE = "newsData";
    private static final String REQUEST_PAGES_COUNT_ATTRIBUTE = "pagesCount";
    private static final String NEWS_VIEW = "newsView.tiles";

    @Autowired
    private NewsManagement manager;
    @Autowired
    private BeanCompiler<NewsSearchCriteria> criteriaCompiler;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            NewsSearchCriteria criteria = criteriaCompiler.constructBean(request);
            int page = Integer.parseInt(request.getParameter(REQUEST_PAGE_PARAMETER));

            List<NewsDetailsTO> newsList = manager.getNewsForPage(criteria, page);
            int pagesCount = manager.getPagesCount(criteria);
            List<Tag> tags = manager.getAllTags();
            List<Author> authors = manager.getAllAuthors();

            request.setAttribute(REQUEST_TAGS_ATTRIBUTE, tags);
            request.setAttribute(REQUEST_AUTHORS_ATTRIBUTE, authors);
            request.setAttribute(REQUEST_SEARCH_CRITERIA_ATTRIBUTE, criteria);
            request.setAttribute(REQUEST_CURRENT_PAGE_ATTRIBUTE, page);
            request.setAttribute(REQUEST_NEWS_DATA_ATTRIBUTE, newsList);
            request.setAttribute(REQUEST_PAGES_COUNT_ATTRIBUTE, pagesCount);

            return NEWS_VIEW;
        } catch (ServiceException | BeanCompilerException e) {
            throw new CommandException();
        }
    }
}
