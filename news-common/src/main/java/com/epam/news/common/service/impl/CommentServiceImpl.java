package com.epam.news.common.service.impl;

import com.epam.news.common.persistence.CommentDAO;
import com.epam.news.common.service.CommentService;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


/**
 * The Comment service.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOG = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDAO dao;

    /**
     * Add new comment for news entry
     *
     * @param comment comment data
     * @return added comment with generated id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment save(Comment comment) throws ServiceException {
        try {
            if (comment.getCommentId() == null) {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                comment.setCreationDate(currentTime);
                return dao.add(comment);
            } else if (!dao.update(comment)){
                throw new ServiceException("Couldn't update data");
            }

            return comment;
        } catch (DAOException e) {
            LOG.error("Error in method: save(Comment comment)", e);
            throw new ServiceException("Couldn't execute comment adding service", e);
        }
    }

    /**
     * Find comment by id
     *
     * @param commentId comment id
     * @return found comment
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public Comment find(Long commentId) throws ServiceException {
        try {
            return dao.find(commentId);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long commentId)", e);
            throw new ServiceException("Couldn't execute comment finding by id service", e);
        }
    }

    /**
     * Delete comment by id
     *
     * @param commentId
     * @return true in case of success
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long commentId) throws ServiceException {
        try {
            return dao.delete(commentId);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long commentId)", e);
            throw new ServiceException("Couldn't execute comment deleting service", e);
        }
    }

    /**
     * Get findAllNewsData comments
     *
     * @return the list of findAllNewsData comments
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Comment> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAllNewsData()", e);
            throw new ServiceException("Couldn't execute getting findAllNewsData comments service", e);
        }
    }

    /**
     * Get news comments list.
     *
     * @param newsId the news id
     * @return the list of comments
     */
    @Override
    public List<Comment> getNewsComments(Long newsId) throws ServiceException {
        try {
            return dao.getNewsComments(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsComments(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news comments service", e);
        }
    }

    /**
     * Delete news comments.
     *
     * @param newsIds the news ids
     */
    @Override
    public void deleteNewsComments(Long... newsIds) throws ServiceException {
        try {
            dao.deleteNewsComments(newsIds);
        } catch (DAOException e) {
            LOG.error("Error in method: deleteNewsComments(newsIds)", e);
            throw new ServiceException("Couldn't execute deleting news comments service", e);
        }
    }
}
