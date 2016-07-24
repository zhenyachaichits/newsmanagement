package com.epam.news.client.controller.command.impl;


import com.epam.news.client.controller.command.Command;
import com.epam.news.client.exception.CommandException;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetNewsDetailsCommand implements Command {

    private static final String REQUEST_NEWS_ID_PARAMETER = "newsId";
    private static final String REQUEST_NEWS_DATA_ATTRIBUTE = "newsData";
    private static final String SINGLE_NEWS_VIEW = "singleNewsView.tiles";

    @Autowired
    private NewsManagement manager;


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Long newsId = Long.parseLong(request.getParameter(REQUEST_NEWS_ID_PARAMETER));

            NewsDetailsTO newsData = manager.getNewsDetails(newsId);
            request.setAttribute(REQUEST_NEWS_DATA_ATTRIBUTE, newsData);

            return SINGLE_NEWS_VIEW;
        } catch (Exception e) {
            throw new CommandException("Couldn't execute getting single news command", e);
        }
    }
}
