package com.epam.news.common.service.impl;

import com.epam.news.common.domain.Author;
import com.epam.news.common.persistence.AuthorDAO;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Author service.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOG = LogManager.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorDAO dao;

    /**
     * Add new author
     *
     * @param author author to be added
     * @return
     * @throws ServiceException if DAOException was thrown
     */
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

    /**
     * Search author by author id
     *
     * @param id author id
     * @throws ServiceException if DAOException was thrown
     * @returnn found author
     */
    @Override
    public Author find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute author finding service", e);
        }
    }

    /**
     * Update author data
     *
     * @param author author to be updated
     * @return true in case of success, else false
     * @throws ServiceException if DAOException was thrown
     */
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

    /**
     * Delete author by id
     *
     * @param id author id
     * @return true in case of success, else false
     * @throws ServiceException if DAOException was thrown
     */
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

    /**
     * Get findAll authors
     *
     * @return list of authors
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Author> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAll()", e);
            throw new ServiceException("Couldn't execute getting findAll authors service", e);
        }
    }

    /**
     * Add authors array.
     *
     * @param authors the authors
     * @return generated id array
     * @throws ServiceException the service exception
     */
    @Override
    public long[] addAuthors(List<Author> authors) throws ServiceException {
        try {
            return dao.addAuthors(authors);
        } catch (DAOException e) {
            LOG.error("Error in method: addAuthors(Author... authors)", e);
            throw new ServiceException("Couldn't execute adding multiple authors service", e);
        }
    }

    /**
     * Add author for news entry
     *
     * @param newsId   the news id
     * @param authorId the author id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsAuthors(long newsId, long... authorId) throws ServiceException {
        try {
            dao.addNewsAuthors(newsId, authorId);
        } catch (DAOException e) {
            LOG.error("Error in method: addNewsAuthors(long newsId, long authorId)", e);
            throw new ServiceException("Couldn't execute adding news author service", e);
        }
    }

    /**
     * Get findAll authors for selected news entry
     *
     * @param newsId the news id
     * @return list of authors
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Author> getNewsAuthors(long newsId) throws ServiceException {
        try {
            return dao.getNewsAuthors(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsAuthors(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news authors service", e);
        }
    }

    /**
     * Delete news authors.
     *
     * @param newsId the news id
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteNewsAuthors(long newsId) throws ServiceException {
        try {
            dao.deleteNewsAuthors(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: deleteNewsAuthors(long newsId)", e);
            throw new ServiceException("Couldn't execute deleting news authors service", e);
        }
    }
}