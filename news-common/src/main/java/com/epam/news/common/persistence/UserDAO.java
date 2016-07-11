package com.epam.news.common.persistence;

import com.epam.news.common.domain.User;
import com.epam.news.common.exception.DAOException;


/**
 * The interface User dao.
 */
public interface UserDAO extends EntityDAO<Long, User> {
    User getUserByLogin(String login) throws DAOException;
}
