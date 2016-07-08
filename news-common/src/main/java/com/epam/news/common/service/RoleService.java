package com.epam.news.common.service;

import com.epam.news.common.domain.Role;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.exception.ServiceException;


/**
 * The interface Role service.
 */
public interface RoleService extends EntityService<Long, Role> {
    /**
     * Updates role value.
     *
     * @param role the role
     * @return true if operation successfully completed
     * @throws DAOException the dao exception
     */
    boolean update(Role role) throws ServiceException;
}
