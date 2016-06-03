package com.epam.news.service.impl;

import com.epam.news.persistence.CommentDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.domain.Comment;
import com.epam.news.service.CommentService;
import com.epam.news.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment add(Comment comment) throws ServiceException {
        try {
            return dao.add(comment);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute comment adding service", e);
        }
    }

    @Override
    public Comment find(Long commentId) throws ServiceException {
        try {
            return dao.find(commentId);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute comment finding by id service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Comment comment) throws ServiceException {
        try {
            return dao.update(comment);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute comment updating service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long commentId) throws ServiceException {
        try {
            return dao.delete(commentId);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute comment deleting service", e);
        }
    }

    @Override
    public List<Comment> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute getting all comments service", e);
        }
    }
}
