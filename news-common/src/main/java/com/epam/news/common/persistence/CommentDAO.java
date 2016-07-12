package com.epam.news.common.persistence;

import com.epam.news.common.domain.Comment;
import com.epam.news.common.exception.DAOException;

import java.util.List;


/**
 * The interface Comment dao.
 */
public interface CommentDAO extends EntityDAO<Long, Comment> {


    /**
     * Add multiple comments data.
     *
     * @param comments the comments
     * @throws DAOException in case of error
     */
    void addComments(Comment... comments) throws DAOException;

    /**
     * Delete multiple comments by id.
     *
     * @param commentIdArray the comment id array
     * @throws DAOException in case of error
     */
    void deleteComments(Long... commentIdArray) throws DAOException;

    /**
     * Delete news comments.
     *
     * @param newsIds the news ids
     * @throws DAOException the dao exception
     */
    void deleteNewsComments(Long... newsIds) throws DAOException;

    /**
     * Gets news' comments.
     *
     * @param newsId news id
     * @return the list of news' comments
     * @throws DAOException in case of error
     */
    List<Comment> getNewsComments(Long newsId) throws DAOException;
}
