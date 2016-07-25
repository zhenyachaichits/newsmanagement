package com.epam.news.client.controller.command.impl;


import com.epam.news.client.controller.command.Command;
import com.epam.news.client.exception.BeanCompilerException;
import com.epam.news.client.exception.CommandException;
import com.epam.news.client.util.RequestUtil;
import com.epam.news.client.util.compiler.BeanCompiler;
import com.epam.news.client.util.validator.BeanValidator;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AddCommentCommand implements Command {

    @Autowired
    private CommentService service;
    @Autowired
    private BeanCompiler<Comment> commentCompiler;
    @Autowired
    private BeanValidator<Comment> commentValidator;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Comment comment = commentCompiler.constructBean(request);
            if (!commentValidator.validate(comment)) {
                throw new CommandException("Invalid comment");
            }
            service.save(comment);

            return RequestUtil.getPrevious(request);
        } catch (BeanCompilerException | ServiceException e) {
            throw new CommandException("Couldn't execute comment adding command", e);
        }
    }
}
