package com.epam.news.service.impl;

import com.epam.news.dao.UserDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.service.UserService;
import com.epam.news.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO dao;

    @Transactional
    public void test() throws ServiceException {
        try {
            dao.all();
        } catch (DAOException e) {
            throw new ServiceException("UserService test method failed", e);
        }
    }
}
