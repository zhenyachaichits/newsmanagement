package com.epam.news.common.service;

import com.epam.news.common.domain.News;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.exception.ServiceException;

import java.util.List;


/**
 * The interface News service.
 */
public interface NewsService extends EntityService<Long, News> {

    /**
     * Gets news by criteria.
     *
     * @param criteria the criteria
     * @return the news by criteria
     * @throws ServiceException the service exception
     */
    List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws ServiceException;

    /**
     * Gets news ordered by comments number.
     *
     * @return the news ordered by comments number
     * @throws ServiceException the service exception
     */
    List<News> getNewsOrderedByCommentsNumber() throws ServiceException;

    /**
     * Gets news count.
     *
     * @return the news count
     * @throws ServiceException the service exception
     */
    int getNewsCount() throws ServiceException;

    /**
     * Gets news count.
     *
     * @param criteria the criteria
     * @return the news count
     * @throws ServiceException the service exception
     */
    int getNewsCount(NewsSearchCriteria criteria) throws ServiceException;

    /**
     * Gets previous news.
     *
     * @param newsId the news id
     * @return the previous news
     * @throws ServiceException the service exception
     */
    long getPreviousNews(long newsId) throws ServiceException;

    /**
     * Gets next news.
     *
     * @param newsId the news id
     * @return the next news
     * @throws ServiceException the service exception
     */
    long getNextNews(long newsId) throws ServiceException;

    /**
     * Gets news for page.
     *
     * @param criteria   the criteria
     * @param pageNumber the page number
     * @param newsOnPage the news on page
     * @return the news for page
     * @throws ServiceException the service exception
     */
    List<News> getNewsForPage(NewsSearchCriteria criteria, int pageNumber, int newsOnPage) throws ServiceException;

    /**
     * Delete news.
     *
     * @param newsIds the news ids
     * @throws ServiceException the service exception
     */
    void deleteNews(Long... newsIds) throws ServiceException;
}
