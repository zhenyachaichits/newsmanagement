package com.epam.news.common.service.impl;

import com.epam.news.common.domain.News;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.persistence.NewsDAO;
import com.epam.news.common.service.NewsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOG = LogManager.getLogger(NewsServiceImpl.class);

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
    public News save(News news) throws ServiceException {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            news.setModificationDate(currentTime);
            if (news.getNewsId() == null) {
                news.setCreationDate(currentTime);
                return dao.add(news);
            } else if (!dao.update(news)) {
                throw new ServiceException("Couldn't update data");
            }

            return news;
        } catch (DAOException e) {
            LOG.error("Error in method: save(News news)", e);
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
     * Get findAllNewsData news
     *
     * @return the list of news
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<News> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method:  findAllNewsData()", e);
            throw new ServiceException("Couldn't execute getting findAllNewsData news service", e);
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
     * Get findAllNewsData news in order of news' comments number
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
            throw new ServiceException("Couldn't execute getting findAllNewsData news ordered by comments service", e);
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

    /**
     * Gets news count.
     *
     * @param criteria the criteria
     * @return the news count
     * @throws ServiceException the service exception
     */
    @Override
    public int getNewsCount(NewsSearchCriteria criteria) throws ServiceException {
        try {
            return dao.getNewsCount(criteria);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsCount()", e);
            throw new ServiceException("Couldn't execute news counting service", e);
        }
    }

    /**
     * Gets previous news.
     *
     * @param newsId the news id
     * @return the previous news
     */
    @Override
    public long getPreviousNews(long newsId) throws ServiceException {
        try {
            return dao.getPreviousNews(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getPreviousNews(newsId)", e);
            throw new ServiceException("Couldn't execute getting previous news  service", e);
        }
    }

    /**
     * Gets next news.
     *
     * @param newsId the news id
     * @return the next news
     */
    @Override
    public long getNextNews(long newsId) throws ServiceException {
        try {
            return dao.getNextNews(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNextNews(newsId)", e);
            throw new ServiceException("Couldn't execute getting next news service", e);
        }
    }

    /**
     * Gets news for page.
     *
     * @param pageNumber the page number
     * @return the news for page
     */
    @Override
    public List<News> getNewsForPage(NewsSearchCriteria criteria, int pageNumber, int newsOnPage) throws ServiceException {
        try {
            if (criteria == null || criteria.getAuthorIdSet() == null
                    || criteria.getTagIdSet() == null) {
                return dao.getNewsForPage(pageNumber, newsOnPage);
            } else {
                return dao.getNewsForPage(criteria, pageNumber, newsOnPage);
            }
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsForPage(pageNumber)", e);
            throw new ServiceException("Couldn't execute getting news for page service", e);
        }
    }

    /**
     * Delete news.
     *
     * @param newsIds the news ids
     * @throws ServiceException the service exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNews(Long... newsIds) throws ServiceException {
        try {
            dao.deleteNews(newsIds);
        } catch (DAOException e) {
            LOG.error("Error in method:deleteNews(newsIds)", e);
            throw new ServiceException("Couldn't execute deleting news service", e);
        }
    }
}
