package com.epam.news.service.impl;

import com.epam.news.persistence.CommentDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.domain.Comment;
import com.epam.news.service.CommentService;
import com.epam.news.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Comment service.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOG = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDAO dao;

    /**
     * 
     * @param comment
     * @return
     * @throws ServiceException
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

    @Override
    public Comment find(Long commentId) throws ServiceException {
        try {
            return dao.find(commentId);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long commentId)", e);
            throw new ServiceException("Couldn't execute comment finding by id service", e);
        }
    }

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

    @Override
    public List<Comment> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            LOG.error("Error in method: all()", e);
            throw new ServiceException("Couldn't execute getting all comments service", e);
        }
    }
}
