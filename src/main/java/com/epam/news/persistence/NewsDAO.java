package com.epam.news.persistence;

import com.epam.news.domain.News;
import com.epam.news.domain.criteria.NewsSearchCriteria;
import com.epam.news.persistence.exception.DAOException;

import java.util.List;
import java.util.Set;

/**
 * The interface News dao.
 */
// TODO: 6/1/2016 set or list?
public interface NewsDAO extends EntityDAO<Long, News> {

    /**
     * Gets news by tags.
     *
     * @param tagIdSet the tag id set
     * @return the news by tags
     * @throws DAOException the dao exception
     */
    List<News> getNewsByTags(Set<Long> tagIdSet) throws DAOException;

    /**
     * Gets news by authors.
     *
     * @param authorSet the author set
     * @return the news by authors
     * @throws DAOException the dao exception
     */
    List<News> getNewsByAuthors(Set<Long> authorSet) throws DAOException;

    /**
     * Gets news by criteria.
     *
     * @param criteria the criteria
     * @return the news by criteria
     * @throws DAOException the dao exception
     */
    List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws DAOException;

    /**
     * Gets news ordered by comments number.
     *
     * @return the news ordered by comments number
     * @throws DAOException the dao exception
     */
    List<News> getNewsOrderedByCommentsNumber() throws DAOException;

    /**
     * Gets news count.
     *
     * @return the news count
     * @throws DAOException the dao exception
     */
    int getNewsCount() throws DAOException;
}
