package com.epam.news.common.service;

import com.epam.news.common.domain.Comment;
import com.epam.news.common.exception.ServiceException;

import java.util.List;


/**
 * The interface Comment service.
 */
public interface CommentService extends EntityService<Long, Comment> {

    /**
     * Get news comments list.
     *
     * @param newsId the news id
     * @return the list of comments
     */
    List<Comment> getNewsComments(long newsId) throws ServiceException;
}