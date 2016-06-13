package com.epam.news.persistence;

import com.epam.news.domain.Comment;
import com.epam.news.exception.DAOException;

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
    void deleteComments(long... commentIdArray) throws DAOException;

    /**
     * Gets news' comments.
     *
     * @param newsId news id
     * @return the list of news' comments
     * @throws DAOException in case of error
     */
    List<Comment> getNewsComments(long newsId) throws DAOException;
}
