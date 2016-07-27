package com.epam.news.client.util.compiler.impl;


import com.epam.news.client.exception.BeanCompilerException;
import com.epam.news.client.util.compiler.BeanCompiler;
import com.epam.news.common.domain.Comment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CommentCompiler implements BeanCompiler<Comment> {

    private static final String REQUEST_NEWS_ID_PARAMETER = "newsId";
    private static final String REQUEST_COMMENT_TEXT_PARAMETER = "commentText";

    @Override
    public Comment constructBean(HttpServletRequest request) throws BeanCompilerException {
        try {
            Long newsId = Long.parseLong(request.getParameter(REQUEST_NEWS_ID_PARAMETER));
            String commentText = request.getParameter(REQUEST_COMMENT_TEXT_PARAMETER);

            Comment comment = new Comment();
            comment.setNewsId(newsId);
            comment.setCommentText(commentText);

            return comment;
        } catch (NumberFormatException e) {
            throw new BeanCompilerException("Invalid data in request", e);
        }
    }
}
