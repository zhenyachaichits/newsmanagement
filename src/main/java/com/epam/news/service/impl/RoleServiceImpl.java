package com.epam.news.service.impl;

import com.epam.news.persistence.RoleDAO;
import com.epam.news.exception.DAOException;
import com.epam.news.domain.Role;
import com.epam.news.service.RoleService;
import com.epam.news.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Role service.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOG = LogManager.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDAO dao;

    /**
     * Add user's role data
     *
     * @param role user role data
     * @return added role
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role add(Role role) throws ServiceException {
        try {
            return dao.add(role);
        } catch (DAOException e) {
            LOG.error("Error in method: add(Role role)", e);
            throw new ServiceException("Couldn't execute role adding service", e);
        }
    }

    /**
     * Search user role data by user id
     *
     * @param userId id of user
     * @return user's role
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public Role find(Long userId) throws ServiceException {
        try {
            return dao.find(userId);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long userId)", e);
            throw new ServiceException("Couldn't execute role finding service", e);
        }
    }

    /**
     * Update user role
     *
     * @param role user role
     * @return true in case of success
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Role role) throws ServiceException {
        try {
            return dao.update(role);
        } catch (DAOException e) {
            LOG.error("Error in method: update(Role role)", e);
            throw new ServiceException("Couldn't execute role updating service", e);
        }
    }

    /**
     * Delete user role by user id
     *
     * @param userId id of user
     * @return true in case of success
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long userId) throws ServiceException {
        try {
            return dao.delete(userId);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long userId)", e);
            throw new ServiceException("Couldn't execute role deleting service", e);
        }
    }

    /**
     * Get findAll users roles
     *
     * @return list of findAll user's roles
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Role> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAll()", e);
            throw new ServiceException("Couldn't execute getting findAll roles service", e);
        }
    }
}
