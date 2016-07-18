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
    public Author save(Author author) throws ServiceException {
        try {
            if (author.getAuthorId() == null) {
                return dao.add(author);
            } else if (!dao.update(author)){
                throw new ServiceException("Couldn't update data");
            }

            return author;
        } catch (DAOException e) {
            LOG.error("Error in method: save(Author author)", e);
            throw new ServiceException("Couldn't execute saving author service", e);
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
     * Get findAllNewsData authors
     *
     * @return list of authors
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Author> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAllNewsData()", e);
            throw new ServiceException("Couldn't execute getting findAllNewsData authors service", e);
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
    public Long[] addAuthors(List<Author> authors) throws ServiceException {
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
     * @param authorIdList the author id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsAuthors(Long newsId, List<Long> authorIdList) throws ServiceException {
        try {
            dao.addNewsAuthors(newsId, authorIdList);
        } catch (DAOException e) {
            LOG.error("Error in method: addNewsAuthors(long newsId, long authorIdList)", e);
            throw new ServiceException("Couldn't execute adding news author service", e);
        }
    }

    /**
     * Get findAllNewsData authors for selected news entry
     *
     * @param newsId the news id
     * @return list of authors
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Author> getNewsAuthors(Long newsId) throws ServiceException {
        try {
            return dao.getNewsAuthors(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsAuthors(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news authors service", e);
        }
    }

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws ServiceException the service exception
     */
    @Override
    public List<Long> getNewsAuthorIds(Long newsId) throws ServiceException {
        try {
            return dao.getNewsAuthorIds(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsAuthorIds(long newsId)", e);
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
    public void deleteNewsAuthors(Long... newsId) throws ServiceException {
        try {
            dao.deleteNewsAuthors(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: deleteNewsAuthors(long newsId)", e);
            throw new ServiceException("Couldn't execute deleting news authors service", e);
        }
    }
}
