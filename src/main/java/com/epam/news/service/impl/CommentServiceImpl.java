package com.epam.news.service.impl;

import com.epam.news.persistence.CommentDAO;
import com.epam.news.exception.DAOException;
import com.epam.news.domain.Comment;
import com.epam.news.service.CommentService;
import com.epam.news.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Comment add(Comment comment) throws ServiceException {
        try {
            return dao.add(comment);
        } catch (DAOException e) {
            LOG.error("Error in method: add(Comment comment)", e);
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
     * Update comment data
     *
     * @param comment comment data
     * @return true in case of success
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Comment comment) throws ServiceException {
        try {
            return dao.update(comment);
        } catch (DAOException e) {
            LOG.error("Error in method: update(Comment comment)", e);
            throw new ServiceException("Couldn't execute comment updating service", e);
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
     * Get findAll comments
     *
     * @return the list of findAll comments
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Comment> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAll()", e);
            throw new ServiceException("Couldn't execute getting findAll comments service", e);
        }
    }

    /**
     * Get news comments list.
     *
     * @param newsId the news id
     * @return the list of comments
     */
    @Override
    public List<Comment> getNewsComments(long newsId) throws ServiceException {
        try {
            return dao.getNewsComments(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsComments(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news comments service", e);
        }
    }
}
