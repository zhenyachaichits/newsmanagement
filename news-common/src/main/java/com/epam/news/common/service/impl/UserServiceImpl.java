package com.epam.news.common.service.impl;

import com.epam.news.common.domain.User;
import com.epam.news.common.persistence.UserDAO;
import com.epam.news.common.service.UserService;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO dao;

    /**
     * Add new user
     *
     * @param user user data
     * @return added user with id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) throws ServiceException {
        try {
            if (user.getUserId() == null) {
                return dao.add(user);
            } else if (!dao.update(user)){
                throw new ServiceException("Couldn't update data");
            }

            return user;
        } catch (DAOException e) {
            LOG.error("Error in method: save(User user)", e);
            throw new ServiceException("Couldn't execute adding new user service", e);
        }
    }

    /**
     * Search user by id
     *
     * @param id user id
     * @return Found user
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public User find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute user finding service", e);
        }
    }

    /**
     * Delete user by id
     *
     * @param id user id
     * @return
     * @throws ServiceException if DAOException was thrown
     */
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

    /**
     * Get findAllNewsData users data
     *
     * @return list of findAllNewsData users
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAllNewsData()", e);
            throw new ServiceException("Couldn't execute getting findAllNewsData users service", e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            return dao.getUserByLogin(login);
        } catch (DAOException e) {
            LOG.error("Error in method: getUserByLogin(login)", e);
            throw new ServiceException("Couldn't execute getting user by login service", e);
        }
    }
}
