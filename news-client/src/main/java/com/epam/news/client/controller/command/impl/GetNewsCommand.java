package com.epam.news.client.controller.command.impl;

import com.epam.news.client.controller.command.Command;
import com.epam.news.client.exception.CommandException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yauhen_Chaichyts on 7/20/2016.
 */

@Component
public class GetNewsCommand implements Command {
    /**
     * Execute command and return string withs page name to be redirected.
     *
     * @param request the request
     * @return the string
     * @throws CommandException the command exception
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
