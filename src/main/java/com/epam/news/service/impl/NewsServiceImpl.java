package com.epam.news.service.impl;

import com.epam.news.domain.News;
import com.epam.news.domain.criteria.NewsSearchCriteria;
import com.epam.news.persistence.NewsDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.service.NewsService;
import com.epam.news.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


/**
 * The News service.
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger LOG = Logger.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsDAO dao;

    /**
     * Add new news entry
     *
     * @param news news data
     * @return added news with generated id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public News add(News news) throws ServiceException {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            news.setCreationDate(currentTime);
            news.setModificationDate(currentTime);

            return dao.add(news);
        } catch (DAOException e) {
            LOG.error("Error in method: add(News news)", e);
            throw new ServiceException("Couldn't execute news adding service", e);
        }
    }

    /**
     * Search news entry by id
     *
     * @param id news id
     * @return found news
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public News find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute news finding service", e);
        }
    }

    /**
     * Update news data
     *
     * @param news news data to be updated
     * @return true in case of success
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(News news) throws ServiceException {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            news.setModificationDate(currentTime);

            return dao.update(news);
        } catch (DAOException e) {
            LOG.error("Error in method: update(News news)", e);
            throw new ServiceException("Couldn't execute news updating service", e);
        }
    }

    /**
     * Delete news entry by id
     *
     * @param id news id
     * @return true if news was deleted
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long id)", e);
            throw new ServiceException("Couldn't execute news deleting service", e);
        }
    }

    /**
     * Get all news
     *
     * @return the list of news
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<News> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            LOG.error("Error in method:  all()", e);
            throw new ServiceException("Couldn't execute getting all news service", e);
        }
    }

    /**
     * Search news by criteria, including authors and tags.
     * If Tags/Authors not selected, news will be found by Authors/Tags only.
     *
     * @param criteria the criteria, containing tags and authors
     * @return the list of news
     * @throws ServiceException if authors and tags not specified or if DAOException was thrown
     */
    @Override
    public List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws ServiceException {
        try {
            if (criteria.getAuthorIdSet().isEmpty() && criteria.getTagIdSet().isEmpty()) {
                throw new ServiceException("Criteria is invalid");
            }
            if (criteria.getAuthorIdSet().isEmpty() && !criteria.getTagIdSet().isEmpty()) {
                return dao.getNewsByTags(criteria.getTagIdSet());
            }
            if (!criteria.getAuthorIdSet().isEmpty() && criteria.getTagIdSet().isEmpty()) {
                return dao.getNewsByAuthors(criteria.getAuthorIdSet());
            }
            return dao.getNewsByCriteria(criteria);
        } catch (DAOException | ServiceException e) {
            LOG.error("Error in method: getNewsByCriteria(NewsSearchCriteria criteria)", e);
            throw new ServiceException("Couldn't execute news searching by criteria service", e);
        }
    }

    /**
     * Get all news in order of news' comments number
     *
     * @return list of news ordered by comments amount
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<News> getNewsOrderedByCommentsNumber() throws ServiceException {
        try {
            return dao.getNewsOrderedByCommentsNumber();
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsOrderedByCommentsNumber()", e);
            throw new ServiceException("Couldn't execute getting all news ordered by comments service", e);
        }
    }

    /**
     * Get news count
     *
     * @return news count
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public int getNewsCount() throws ServiceException {
        try {
            return dao.getNewsCount();
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsCount()", e);
            throw new ServiceException("Couldn't execute news counting service", e);
        }
    }
}
