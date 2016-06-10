package com.epam.news.persistence;

import com.epam.news.domain.Comment;
import com.epam.news.persistence.exception.DAOException;

import java.util.List;


/**
 * The interface Comment dao.
 */
public interface CommentDAO extends EntityDAO<Long, Comment> {
    /**
     * Gets news' comments.
     *
     * @param newsId news id
     * @return the list of news' comments
     * @throws DAOException in case of error
     */
    List<Comment> getNewsComments(long newsId) throws DAOException;
}
