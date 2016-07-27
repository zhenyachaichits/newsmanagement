package com.epam.news.client.controller.command;

import com.epam.news.client.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute command and return string withs page name to be redirected.
     *
     * @param request the request
     * @return the string
     * @throws CommandException the command exception
     */
    String execute(HttpServletRequest request) throws CommandException;
}
