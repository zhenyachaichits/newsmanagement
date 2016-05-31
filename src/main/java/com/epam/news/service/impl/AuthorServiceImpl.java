package com.epam.news.service.impl;

import com.epam.news.dao.AuthorDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.domain.Author;
import com.epam.news.service.AuthorService;
import com.epam.news.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Author add(Author author) throws ServiceException {
        try {
            return dao.add(author);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute adding author service", e);
        }
    }

    @Override
    public Author find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute author finding service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Author author) throws ServiceException {
        try {
            return dao.update(author);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute author updating service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute author deleting service", e);
        }
    }

    @Override
    public List<Author> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute getting all authors service", e);
        }
    }
}
