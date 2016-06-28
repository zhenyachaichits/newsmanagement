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
     * Gets previous news.
     *
     * @param newsId the news id
     * @return the previous news
     */
    News getPreviousNews(long newsId) throws ServiceException;

    /**
     * Gets next news.
     *
     * @param newsId the news id
     * @return the next news
     */
    News getNextNews(long newsId) throws ServiceException;
}
