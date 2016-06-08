package com.epam.news.service.impl;

import com.epam.news.persistence.AuthorDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.domain.Author;
import com.epam.news.service.AuthorService;
import com.epam.news.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The type Author service.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOG = Logger.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Author add(Author author) throws ServiceException {
        try {
            return dao.add(author);
        } catch (DAOException e) {
            LOG.error("Error in method: add(Author author)", e);
            throw new ServiceException("Couldn't execute adding author service", e);
        }
    }

    @Override
    public Author find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute author finding service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Author author) throws ServiceException {
        try {
            return dao.update(author);
        } catch (DAOException e) {
            LOG.error("Error in method: update(Author author)", e);
            throw new ServiceException("Couldn't execute author updating service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            LOG.error("Error in method: update(Author author)", e);
            throw new ServiceException("Couldn't execute author deleting service", e);
        }
    }

    @Override
    public List<Author> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            LOG.error("Error in method: all()", e);
            throw new ServiceException("Couldn't execute getting all authors service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsAuthor(long newsId, long authorId) throws ServiceException {
        try {
            dao.addNewsAuthor(newsId, authorId);
        } catch (DAOException e) {
            LOG.error("Error in method: addNewsAuthor(long newsId, long authorId)", e);
            throw new ServiceException("Couldn't execute adding news author service", e);
        }
    }

    @Override
    public List<Author> getNewsAuthors(long newsId) throws ServiceException {
        try {
            return dao.getNewsAuthors(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsAuthors(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news authors service", e);
        }
    }
}
