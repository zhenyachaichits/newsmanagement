package com.epam.news.common.service;

import com.epam.news.common.domain.User;
import com.epam.news.common.exception.ServiceException;


/**
 * The interface User service.
 */
public interface UserService extends EntityService<Long, User> {
    User getUserByLogin(String login) throws ServiceException;
}
