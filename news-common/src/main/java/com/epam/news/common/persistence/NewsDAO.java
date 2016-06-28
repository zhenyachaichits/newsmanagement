package com.epam.news.common.persistence;

import com.epam.news.common.domain.News;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.exception.DAOException;

import java.util.List;
import java.util.Set;

/**
 * The interface News dao.
 */
// TODO: 6/1/2016 set or list?
public interface NewsDAO extends EntityDAO<Long, News> {

    /**
     * Gets news list by tags.
     *
     * @param tagIdSet the tag id set
     * @return the list of news
     * @throws DAOException the dao exception
     */
    List<News> getNewsByTags(Set<Long> tagIdSet) throws DAOException;

    /**
     * Gets news list by authors.
     *
     * @param authorSet the author set
     * @return the list of news
     * @throws DAOException the dao exception
     */
    List<News> getNewsByAuthors(Set<Long> authorSet) throws DAOException;

    /**
     * Gets news by criteria.
     *
     * @param criteria the criteria
     * @return the list of news
     * @throws DAOException the dao exception
     */
    List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws DAOException;

    /**
     * Gets news list ordered by comments number.
     *
     * @return the news ordered by comments number
     * @throws DAOException the dao exception
     */
    List<News> getNewsOrderedByCommentsNumber() throws DAOException;

    /**
     * Gets findAllNewsData news count.
     *
     * @return the news count
     * @throws DAOException the dao exception
     */
    int getNewsCount() throws DAOException;

    /**
     * Gets previous news.
     *
     * @param newsId the news id
     * @return the previous news
     */
    long getPreviousNews(long newsId) throws DAOException;

    /**
     * Gets next news.
     *
     * @param newsId the news id
     * @return the next news
     */
    long getNextNews(long newsId) throws DAOException;
}
