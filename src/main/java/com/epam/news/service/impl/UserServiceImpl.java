package com.epam.news.service.impl;

import com.epam.news.persistence.UserDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.domain.User;
import com.epam.news.service.UserService;
import com.epam.news.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User add(User user) throws ServiceException {
        try {
            return dao.add(user);
        } catch (DAOException e) {
            LOG.error("Error in method: add(User user)", e);
            throw new ServiceException("Couldn't execute adding new user service", e);
        }
    }

    @Override
    public User find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute user finding service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(User user) throws ServiceException {
        try {
            return dao.update(user);
        } catch (DAOException e) {
            LOG.error("Error in method: update(User user)", e);
            throw new ServiceException("Couldn't execute user updating service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long id)", e);
            throw new ServiceException("Couldn't execute user deleting service", e);
        }
    }

    @Override
    public List<User> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            LOG.error("Error in method: all()", e);
            throw new ServiceException("Couldn't execute getting all users service", e);
        }
    }
}
